package com.codex.mystore.config;

import com.codex.mystore.dao.repo.TeamMemberRepository;
import com.codex.mystore.models.team.TeamMember;
import com.codex.mystore.models.team.TeamPermission;
import com.codex.mystore.models.team.TeamRole;
import com.codex.mystore.models.user.MyUserDetails;
import com.codex.mystore.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Component
public class TeamAuthorityFilter extends OncePerRequestFilter {

    @Autowired
    TeamMemberRepository teamMemberRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String path = request.getServletPath();

            final String regex = "\\/api\\/project\\/(\\d*)(\\/)*";

            final Pattern pattern = Pattern.compile(regex);
            final Matcher matcher = pattern.matcher(path);

            String project_id = null;
            if(matcher.find()){
                project_id = matcher.group(1);
            }

            Long proj_id = Long.parseLong(project_id);


            MyUserDetails myUser = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            List<TeamMember> teamMemberDetail = teamMemberRepository.findTeamMemberByUser(new User(myUser.getId()));

            Optional<TeamMember> singleTeamMember = teamMemberDetail.stream().filter(x -> x.getTeam().getProject().getId() == proj_id).findFirst();

            List<GrantedAuthority> teamRoles = singleTeamMember.get().getTeamRoles().stream().map(x -> new SimpleGrantedAuthority(x.getName())).collect(Collectors.toList());
            List<GrantedAuthority> teamPermission = singleTeamMember.get().getTeamRoles().stream()
                    .flatMap(x -> x.getPermissions().stream().map(TeamPermission::getName))
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            List<GrantedAuthority> authorities = (List<GrantedAuthority>) myUser.getAuthorities();
            authorities.addAll(teamRoles);
            authorities.addAll(teamPermission);
            myUser.setAuthorities(authorities);
            //new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(myUser, null, myUser.getAuthorities()));
        }catch (Exception ex){
            logger.error("Failed to set team authorities {}", ex);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        return !path.startsWith("/api/project/");
    }
}
