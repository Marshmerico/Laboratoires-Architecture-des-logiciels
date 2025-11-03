package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * AssignTicketRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-11-01T21:15:30.678-04:00")


public class AssignTicketRequest   {
  @JsonProperty("adminId")
  private Integer adminId = null;

  public AssignTicketRequest adminId(Integer adminId) {
    this.adminId = adminId;
    return this;
  }

  /**
   * ID de l'administrateur à qui assigner le ticket
   * @return adminId
  **/
  @ApiModelProperty(example = "1", required = true, value = "ID de l'administrateur à qui assigner le ticket")
  @NotNull


  public Integer getAdminId() {
    return adminId;
  }

  public void setAdminId(Integer adminId) {
    this.adminId = adminId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AssignTicketRequest assignTicketRequest = (AssignTicketRequest) o;
    return Objects.equals(this.adminId, assignTicketRequest.adminId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(adminId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AssignTicketRequest {\n");
    
    sb.append("    adminId: ").append(toIndentedString(adminId)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

