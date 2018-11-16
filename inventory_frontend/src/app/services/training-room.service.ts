import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TrainingRoom } from '../models/training-room';

@Injectable({
  providedIn: 'root'
})
export class TrainingRoomService {

  constructor(private http: HttpClient) { }

  getRooms(){
    return this.http.get('/training_rooms');
  }

  addRoom(room: TrainingRoom){
    return this.http.post('/training_rooms', room);
  }

  updateRoom(room: TrainingRoom){
    return this.http.put('/training_rooms',room);
  }
}
