package org.cswteams.ms3.dto.preferences;

import lombok.Getter;

@Getter
public class PreferenceInWithUIDDTO {

    private final Long doctorId;
    private final PreferenceDTOIn dto ;

    /**
     *
     * @param doctorId The id of the doctor to whom the event is related
     * @param dto The DTO representing the preference to insert
     */
    public PreferenceInWithUIDDTO(Long doctorId, PreferenceDTOIn dto) {
        this.doctorId = doctorId;
        this.dto = dto ;
    }
}
