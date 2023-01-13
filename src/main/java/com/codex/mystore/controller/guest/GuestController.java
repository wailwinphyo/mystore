package com.codex.mystore.controller.guest;

import com.codex.mystore.models.guest.Guest;
import com.codex.mystore.network.request.GuestRequest;
import com.codex.mystore.services.GuestService;
import com.codex.mystore.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/guest")
public class GuestController {

    @Autowired
    GuestService guestService;

    @Autowired
    DateUtils dateUtils;

    @PutMapping("/create")
    public ResponseEntity<?> createGuest(@RequestBody GuestRequest guestRequest) {
        Guest guest = guestService.findByEmail(guestRequest.getGuestEmail());
        if (guest != null) {
            return ResponseEntity.ok("Email Already Exist");
        }

        String currentDateTime = dateUtils.currentDateAndTime();
        Guest tempGuest = new Guest();

        tempGuest.setGuestEmail(guestRequest.getGuestEmail());
        tempGuest.setGuestName(guestRequest.getGuestName());
        tempGuest.setUpdateAt(currentDateTime);
        tempGuest.setCreateAt(currentDateTime);
        tempGuest.setGuestPhoneNumber(guestRequest.getGuestPhoneNumber());
        return new ResponseEntity<>( guestService.createGuest(tempGuest), HttpStatus.CREATED);
    }
}
