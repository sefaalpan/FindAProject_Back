package be.ros.FindAProject.mappers.impl;

import be.ros.FindAProject.mappers.BaseMapper;
import be.ros.FindAProject.models.dtos.CategorieDto;
import be.ros.FindAProject.models.entities.Categorie;
import be.ros.FindAProject.models.forms.CategorieForm;
import org.springframework.stereotype.Service;

@Service
public class CategorieMapper implements BaseMapper<Categorie, CategorieDto, CategorieForm> {
    @Override
    public CategorieDto toDto(Categorie entity) {
        if (entity == null)
            return null;

        return CategorieDto.builder()
                .id(entity.getId())
                .label(entity.getLabel())
                .build();

    }

    @Override
    public Categorie dtoToEntity(CategorieDto dto) {
        if (dto == null)
            return null;

        return Categorie.builder()
                .id(dto.getId())
                .label(dto.getLabel())
                .build();
    }

    @Override
    public Categorie formToEntity(CategorieForm form) {
        if (form == null)
            return null;

        return Categorie.builder()
                .label(form.getLabel())
                .build();
    }
}
