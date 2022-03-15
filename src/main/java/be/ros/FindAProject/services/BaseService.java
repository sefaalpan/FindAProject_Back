package be.ros.FindAProject.services;

import java.util.List;

public interface BaseService<TDTO, TFORM, TID> {

    List<TDTO> getAll();
    TDTO getOneById(TID id);
    TDTO insert(TFORM form);
    TDTO update(TFORM form, TID id);
    TDTO deleteById(TID id);
//    PageDto<TDTO> getEntitiesPageable(Integer page, Integer size);
}
