package licenta_backend.dtos;

public class UserPlannerDTO {
    private UserDTO userDTO;
    private PlannerDTO plannerDTO;

    public UserPlannerDTO() {
    }

    public UserPlannerDTO(UserDTO userDTO, PlannerDTO plannerDTO) {
        this.userDTO = userDTO;
        this.plannerDTO = plannerDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public PlannerDTO getPlannerDTO() {
        return plannerDTO;
    }

    public void setPlannerDTO(PlannerDTO plannerDTO) {
        this.plannerDTO = plannerDTO;
    }
}