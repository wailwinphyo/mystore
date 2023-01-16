package com.codex.mystore.dao.repo;

import com.codex.mystore.models.team.TeamMember;
import com.codex.mystore.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {

    public List<TeamMember> findTeamMemberByUser(User user);
}
