package be.ros.FindAFreelance.mappers;

public interface BaseMapper<TENTITY, TDTO, TFORM> {

    TDTO toDto(TENTITY entity);
    TENTITY dtoToEntity(TDTO dto);
    TENTITY formToEntity(TFORM form);


}
