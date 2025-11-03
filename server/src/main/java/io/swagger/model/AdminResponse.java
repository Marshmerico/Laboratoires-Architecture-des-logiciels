package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * AdminResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-11-01T21:15:30.678-04:00")


public class AdminResponse   {
  @JsonProperty("adminId")
  private Integer adminId = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("ticketsId")
  @Valid
  private List<Integer> ticketsId = null;

  public AdminResponse adminId(Integer adminId) {
    this.adminId = adminId;
    return this;
  }

  /**
   * Get adminId
   * @return adminId
  **/
  @ApiModelProperty(example = "1", value = "")


  public Integer getAdminId() {
    return adminId;
  }

  public void setAdminId(Integer adminId) {
    this.adminId = adminId;
  }

  public AdminResponse name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(example = "Admin1", value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public AdminResponse email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  **/
  @ApiModelProperty(example = "admin1@mail.com", value = "")


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public AdminResponse ticketsId(List<Integer> ticketsId) {
    this.ticketsId = ticketsId;
    return this;
  }

  public AdminResponse addTicketsIdItem(Integer ticketsIdItem) {
    if (this.ticketsId == null) {
      this.ticketsId = new ArrayList<Integer>();
    }
    this.ticketsId.add(ticketsIdItem);
    return this;
  }

  /**
   * Get ticketsId
   * @return ticketsId
  **/
  @ApiModelProperty(example = "[5,7]", value = "")


  public List<Integer> getTicketsId() {
    return ticketsId;
  }

  public void setTicketsId(List<Integer> ticketsId) {
    this.ticketsId = ticketsId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AdminResponse adminResponse = (AdminResponse) o;
    return Objects.equals(this.adminId, adminResponse.adminId) &&
        Objects.equals(this.name, adminResponse.name) &&
        Objects.equals(this.email, adminResponse.email) &&
        Objects.equals(this.ticketsId, adminResponse.ticketsId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(adminId, name, email, ticketsId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AdminResponse {\n");
    
    sb.append("    adminId: ").append(toIndentedString(adminId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    ticketsId: ").append(toIndentedString(ticketsId)).append("\n");
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

