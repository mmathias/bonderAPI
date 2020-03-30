package com.bonder.dto;

import lombok.Data;

@Data
public class InvitationDTO {
    private Long eventId;
    private Long invitedId;
    private Long inviterId;
}
