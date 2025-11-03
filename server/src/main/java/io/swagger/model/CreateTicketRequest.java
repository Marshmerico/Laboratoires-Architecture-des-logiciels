package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CreateTicketRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2025-11-01T21:15:30.678-04:00")


public class CreateTicketRequest   {
  @JsonProperty("title")
  private String title = null;

  @JsonProperty("description")
  private String description = null;

  /**
   * Gets or Sets priority
   */
  public enum PriorityEnum {
    HAUTE("haute"),
    
    MOYENNE("moyenne"),
    
    BASSE("basse");

    private String value;

    PriorityEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static PriorityEnum fromValue(String text) {
      for (PriorityEnum b : PriorityEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("priority")
  private PriorityEnum priority = null;

  @JsonProperty("creatorId")
  private Integer creatorId = null;

  public CreateTicketRequest title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
  **/
  @ApiModelProperty(example = "Bug dans l'application", required = true, value = "")
  @NotNull

@Size(min=1) 
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public CreateTicketRequest description(String description) {
    this.description = description;
    return this;
  }



  /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(example = "L'application crash au démarrage", required = true, value = "")
  @NotNull

@Size(min=1) 
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public CreateTicketRequest priority(PriorityEnum priority) {
    this.priority = priority;
    return this;
  }

  /**
   * Get priority
   * @return priority
  **/
  @ApiModelProperty(example = "haute", required = true, value = "")
  @NotNull


  public PriorityEnum getPriority() {
    return priority;
  }

  public void setPriority(PriorityEnum priority) {
    this.priority = priority;
  }

  public CreateTicketRequest creatorId(Integer creatorId) {
    this.creatorId = creatorId;
    return this;
  }

  /**
   * ID de l'utilisateur créateur
   * @return creatorId
  **/
  @ApiModelProperty(example = "1", required = true, value = "ID de l'utilisateur créateur")
  @NotNull


  public Integer getCreatorId() {
    return creatorId;
  }

  public void setCreatorId(Integer creatorId) {
    this.creatorId = creatorId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateTicketRequest createTicketRequest = (CreateTicketRequest) o;
    return Objects.equals(this.title, createTicketRequest.title) &&
        Objects.equals(this.description, createTicketRequest.description) &&
        Objects.equals(this.priority, createTicketRequest.priority) &&
        Objects.equals(this.creatorId, createTicketRequest.creatorId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, description, priority, creatorId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateTicketRequest {\n");
    
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    priority: ").append(toIndentedString(priority)).append("\n");
    sb.append("    creatorId: ").append(toIndentedString(creatorId)).append("\n");
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

