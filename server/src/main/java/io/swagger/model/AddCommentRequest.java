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
 * AddCommentRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-11-01T21:15:30.678-04:00")


public class AddCommentRequest   {
  @JsonProperty("comment")
  private String comment = null;

  @JsonProperty("userId")
  private Integer userId = null;

  public AddCommentRequest comment(String comment) {
    this.comment = comment;
    return this;
  }

  /**
   * Get comment
   * @return comment
  **/
  @ApiModelProperty(example = "J'ai trouvé la source du problème", required = true, value = "")
  @NotNull

@Size(min=1) 
  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public AddCommentRequest userId(Integer userId) {
    this.userId = userId;
    return this;
  }

  /**
   * ID de l'utilisateur qui commente
   * @return userId
  **/
  @ApiModelProperty(example = "1", required = true, value = "ID de l'utilisateur qui commente")
  @NotNull


  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AddCommentRequest addCommentRequest = (AddCommentRequest) o;
    return Objects.equals(this.comment, addCommentRequest.comment) &&
        Objects.equals(this.userId, addCommentRequest.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(comment, userId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AddCommentRequest {\n");
    
    sb.append("    comment: ").append(toIndentedString(comment)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
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

