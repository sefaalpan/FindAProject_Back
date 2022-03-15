package be.ros.FindAProject.mappers.impl;

import be.ros.FindAProject.mappers.BaseMapper;
import be.ros.FindAProject.models.dtos.ProjetDto;
import be.ros.FindAProject.models.entities.Categorie;
import be.ros.FindAProject.models.entities.Projet;
import be.ros.FindAProject.models.entities.User;
import be.ros.FindAProject.models.forms.ProjetForm;
import be.ros.FindAProject.services.UserService;
import be.ros.FindAProject.services.impl.CategorieServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ProjetMapper implements BaseMapper<Projet, ProjetDto, ProjetForm> {

    private final CategorieMapper categorieMapper;
    private final UserMapper userMapper;
    private final UserService userService;
    private final CategorieServiceImpl categorieService;

    public ProjetMapper(CategorieMapper categorieMapper, UserMapper userMapper, UserService userService, CategorieServiceImpl categorieService) {
        this.categorieMapper = categorieMapper;
        this.userMapper = userMapper;
        this.userService = userService;
        this.categorieService = categorieService;
    }

    @Override
    public ProjetDto toDto(Projet entity) {
        if (entity == null)
            return null;

        return ProjetDto.builder()
                .projet_id(entity.getProjet_id())
                .prix(entity.getPrix())
                .date(entity.getDate())
                .titre(entity.getTitre())
                .resume(entity.getResume())
                .username(entity.getUser().getUsername())
//                .user(userMapper.toDto(entity.getUser()))
                .categorie(categorieMapper.toDto(entity.getCategorie()))
                .reserve(entity.isReserve())
                .build();
    }

    @Override
    public Projet dtoToEntity(ProjetDto dto) {
        if (dto == null) return null;
        Projet projet = new Projet();

        projet.setUser((User) userService.loadUserByUsername(dto.getUsername()));
//        projet.setUser(userMapper.dtoToEntity(dto.getUser()));
        projet.setProjet_id(dto.getProjet_id());
        projet.setCategorie(categorieMapper.dtoToEntity(dto.getCategorie()));
        projet.setPrix(dto.getPrix());
        projet.setDate(dto.getDate());
        projet.setResume(dto.getResume());
        projet.setTitre(dto.getTitre());
        projet.setReserve(dto.isReserve());
        return projet;
    }

    @Override
    public Projet formToEntity(ProjetForm form) {
        if (form == null) return null;

        Projet projet = new Projet();
        projet.setUser((User) userService.loadUserByUsername(form.getUserSimpleForm().getUsername()));
        projet.setTitre(form.getTitre());
        projet.setResume(form.getResume());
        projet.setPrix(form.getPrix());
        projet.setDate(form.getDate());
        projet.setReserve(false);

        Categorie categorie = categorieMapper.dtoToEntity(categorieService.findByLabel(form.getCategorie().getLabel()));
        if (categorie == null)
            categorie = categorieMapper.dtoToEntity(categorieService.insert(form.getCategorie()));

        projet.setCategorie(categorie);

        return projet;
    }
}
