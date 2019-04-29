import {Component, OnDestroy, OnInit} from '@angular/core';
import {RxStompService} from '@stomp/ng2-stompjs';
import { Message } from '@stomp/stompjs';
import {Subscription} from 'rxjs';
import {Greeting} from '../models/Greeting';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit, OnDestroy {

  public receivedMessages: Greeting[] = [];

  private topicSubscription: Subscription;



  constructor(private rxStompService: RxStompService) { }

  ngOnInit() {
    this.topicSubscription = this.rxStompService.watch('/topic/greetings').subscribe((message: Message) => {
      console.log(message);
      console.log('message body ', message.body);
      this.receivedMessages.push(JSON.parse(message.body));
    });
  }

  ngOnDestroy() {
    this.topicSubscription.unsubscribe();
  }


  onSendMessage() {
    const message = {name: 'Manoj'};
    this.rxStompService.publish({destination: '/app/hello', body: JSON.stringify(message)});
  }

}
