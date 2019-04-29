import {JsonProperty} from 'json-object-mapper';

export class Greeting {

  @JsonProperty()
  public content: string = null;

}
