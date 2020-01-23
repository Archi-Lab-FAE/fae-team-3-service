package de.th.koeln.archilab.fae.faeteam3service.ausnahmesituation;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface AusnahmesituationMapper {
    AusnahmesituationDTO toAusnahmesituationDTO(Ausnahmesituation ausnahmesituation);

    List<AusnahmesituationDTO> toAusnahmesituationDTOs(List<Ausnahmesituation> ausnahmesituationen);

    Ausnahmesituation toAusnahmesituation(AusnahmesituationDTO ausnahmesituationDTO);
}
