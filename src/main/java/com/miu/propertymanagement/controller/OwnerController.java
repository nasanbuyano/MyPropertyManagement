package com.miu.propertymanagement.controller;

import com.miu.propertymanagement.domain.dto.PropertyRequestDTO;
import com.miu.propertymanagement.domain.enums.OfferStatus;
import com.miu.propertymanagement.service.OfferService;
import com.miu.propertymanagement.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
@CrossOrigin("*")
public class OwnerController {
    @Autowired
    private OfferService offerService;

    @Autowired
    private PropertyService propertyService;

    @GetMapping("owner/{userId}/offer")
    ResponseEntity<?> getOffersByOwner(@PathVariable("userId") Integer userId
    ) {
        return ResponseEntity.ok(offerService.getOwnerOffersById(userId));
    }
    @GetMapping("owner/{userId}/historyOffers")
    ResponseEntity<?> getHistoryOffersByOwner(@PathVariable("userId") Integer userId
    ) {
        return ResponseEntity.ok(offerService.findHistoryOffersByOwner(userId));
    }

    @GetMapping("owner/{userId}/liveOffers")
    ResponseEntity<?> getLiveOffersByOwner(@PathVariable("userId") Integer userId
    ) {
        return ResponseEntity.ok(offerService.findLiveOffersByOwner(userId));
    }



    @GetMapping("/owner/{userId}/offer/{offerId}")
    ResponseEntity<?> changeOfferStatus(@PathVariable("userId") Integer userId,
                                        @PathVariable("offerId") Integer offerId,
                                        @RequestParam(value = "changeOfferStatus") OfferStatus change) {
        return ResponseEntity.ok(offerService.changeOfferStatus(userId, offerId, change));
    }

    @GetMapping("/owner/{userId}/property")
    ResponseEntity<?> getAllPropertyByOwner(@PathVariable("userId") Integer userId) {
        return ResponseEntity.ok(propertyService.getAllPropertyByOwner(userId));
    }

    @PostMapping("/owner/{userId}/property")
    ResponseEntity<?> addProperty(@RequestBody PropertyRequestDTO propertyRequestDTO, @PathVariable Integer userId) {
        return ResponseEntity.ok(propertyService.addProperty(userId,propertyRequestDTO));
    }

    @GetMapping("/owner/{userId}/property/{propertyId}/CancelContingency")
    ResponseEntity<?> CancelContingency(@PathVariable("userId") Integer userId, @PathVariable("propertyId") Integer propertyId) {
        return ResponseEntity.ok(propertyService.CancelContingency(userId,propertyId));
    }
}
