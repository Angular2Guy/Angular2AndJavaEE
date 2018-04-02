import { Component, OnInit, ViewChild, ElementRef, OnDestroy } from '@angular/core';

let that: CrrootComponent = null;

@Component({
  selector: 'app-crroot',
  templateUrl: './crroot.component.html',
  styleUrls: ['./crroot.component.scss']
})
export class CrrootComponent implements OnInit,OnDestroy {
  
  title = 'Please choose a link.';
  @ViewChild('myCanvas') canvas: ElementRef;
  ctx: CanvasRenderingContext2D;
  width: number; 
  height: number;
  ballArray = [];
  
  constructor() { }

  ngOnInit() {      
      that = this;
      this.ctx = this.canvas.nativeElement.getContext('2d');
      this.width = this.canvas.nativeElement.width;
      this.height = this.canvas.nativeElement.height;
      
      this.createBalls(10);      

      window.requestAnimationFrame(this.mainLoop);
  }

  ngOnDestroy(): void {
      that = null;
  }
    
  createBalls(numberOfBalls) {
      let mywidth = this.width;
      let myheight = this.height;
      let myctx = this.ctx;
      for(let i=0; i < numberOfBalls; i++) {
        // Create a ball with random position and speed
        let ball =  new Ball(mywidth*Math.random(), myheight*Math.random(), (10*Math.random())-5, (10*Math.random())-5, 40, myctx); 
        // Add it to the array
        this.ballArray.push(ball);
      }              
    }  
      
  mainLoop() {
      if(that === null) return;
      // vasClear the can
      that.ctx.clearRect(0, 0, that.width, that.height);
      // For each ball in the array
      for(let i=0; i < that.ballArray.length; i++) {      
        let balls = that.ballArray[i];
        
        // 1) Move the ball
        balls.move();   
    
        // 2) collision test with walls
        that.collisionTestWithWalls(balls);
    
        // 3) draw the ball
        balls.draw();
    }
    
    that.collisionTestBetweenBalls();
    
    // Ask for new animation frame
    window.requestAnimationFrame(that.mainLoop);
  }
   
  collisionTestWithWalls(ball) {
      if (ball.x < ball.radius) {
          ball.x = ball.radius;
          ball.vx *= -1;
      } 
      if (ball.x > this.width - (ball.radius)) {
          ball.x = this.width - (ball.radius);
          ball.vx *= -1;
      }     
      if (ball.y < ball.radius) {
          ball.y = ball.radius;
          ball.vy *= -1;
      }     
      if (ball.y > this.height - (ball.radius)) {
          ball.y = this.height - (ball.radius);
          ball.vy *= -1;
      }
  }

  collisionTestBetweenBalls() {  
    let balls = this.ballArray;
    
    for (let i = 0; i < this.ballArray.length; i++) {
          for (let j = i + 1; j < this.ballArray.length; j++) {
              let dx = balls[j].x - balls[i].x;
              let dy = balls[j].y - balls[i].y;
            
              let dist = Math.sqrt(dx * dx + dy * dy);
              if (dist < (balls[j].radius + balls[i].radius)) {
                  // balls have contact so push back...
                  let normalX = dx / dist;
                  let normalY = dy / dist;
                  let middleX = (balls[i].x + balls[j].x) / 2;
                  let middleY = (balls[i].y + balls[j].y) / 2;
                
                  balls[i].x = middleX - normalX * balls[i].radius;
                  balls[i].y = middleY - normalY * balls[i].radius;
                  balls[j].x = middleX + normalX * balls[j].radius;
                  balls[j].y = middleY + normalY * balls[j].radius;
                
                  let dVector = (balls[i].vx - balls[j].vx) * normalX;
                  dVector += (balls[i].vy - balls[j].vy) * normalY;
                  let dvx = dVector * normalX;
                  let dvy = dVector * normalY;
                
                  balls[i].vx -= dvx;
                  balls[i].vy -= dvy;
                  balls[j].vx += dvx;
                  balls[j].vy += dvy;
              }
          }
      }
  }
}

class Ball {        
    radius = 0;
        
    constructor (public x: number, public y: number, public vx: number, public vy: number, diameter: number, private ctx: CanvasRenderingContext2D) {
        this.radius = diameter/2;
    }
    
    draw() {
      this.ctx.beginPath();
      this.ctx.arc(this.x, this.y, this.radius, 0, 2*Math.PI);
      this.ctx.fill();
    };
    
    move() {
      this.x += this.vx;
      this.y += this.vy;
    };
    
  }