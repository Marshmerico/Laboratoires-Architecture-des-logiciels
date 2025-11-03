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
 * UserResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-11-01T21:15:30.678-04:00")


public class UserResponse   {
  @JsonProperty("userId")
  private Integer userId = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("role")
  private String role = null;

  @JsonProperty("ticketsId")
  @Valid
  private List<Integer> ticketsId = null;

  public UserResponse userId(Integer userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  **/
  @ApiModelProperty(example = "1", value = "")


  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public UserResponse name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(example = "Alice", value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UserResponse email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  **/
  @ApiModelProperty(example = "alice@mail.com", value = "")


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UserResponse role(String role) {
    this.role = role;
    return this;
  }

  /**
   * Get role
   * @return role
  **/
  @ApiModelProperty(example = "DEV", value = "")


  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public UserResponse ticketsId(List<Integer> ticketsId) {
    this.ticketsId = ticketsId;
    return this;
  }

  public UserResponse addTicketsIdItem(Integer ticketsIdItem) {
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
  @ApiModelProperty(example = "[1,2,3]", value = "")


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
    UserResponse userResponse = (UserResponse) o;
    return Objects.equals(this.userId, userResponse.userId) &&
        Objects.equals(this.name, userResponse.name) &&
        Objects.equals(this.email, userResponse.email) &&
        Objects.equals(this.role, userResponse.role) &&
        Objects.equals(this.ticketsId, userResponse.ticketsId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, name, email, role, ticketsId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserResponse {\n");
    
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
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

