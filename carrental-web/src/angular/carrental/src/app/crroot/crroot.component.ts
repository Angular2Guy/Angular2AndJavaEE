import { Component, OnInit, ViewChild, ElementRef, OnDestroy } from '@angular/core';


@Component({
  selector: 'app-crroot',
  templateUrl: './crroot.component.html',
  styleUrls: ['./crroot.component.scss']
})
export class CrrootComponent implements OnInit {

  title = 'Please choose a link.';
  @ViewChild('myCanvas', { static: true }) canvas: ElementRef;
  ctx: CanvasRenderingContext2D;
  width: number;
  height: number;
  ballArray = [];

  constructor() { }

  ngOnInit() {
      this.ctx = this.canvas.nativeElement.getContext('2d');
      this.width = this.canvas.nativeElement.width;
      this.height = this.canvas.nativeElement.height;

      this.createBalls(10);

      window.requestAnimationFrame(() => this.mainLoop());
  }

  createBalls(numberOfBalls) {
      const mywidth = this.width;
      const myheight = this.height;
      const myctx = this.ctx;
      for(let i=0; i < numberOfBalls; i++) {
        // Create a ball with random position and speed
        const color = this.rgbToHex(Math.round(255*Math.random()), Math.round(255*Math.random()),Math.round(255*Math.random()));
//        console.log(color);
        const ball =  new Ball(mywidth*Math.random(), myheight*Math.random(), (10*Math.random())-5, (10*Math.random())-5, 40, myctx, color);
        // Add it to the array
        this.ballArray.push(ball);
      }
    }

  componentToHex(c: number): String {
      const hex = c.toString(16);
      return hex.length == 1 ? '0' + hex : hex;
  }

  rgbToHex(r: number, g: number, b: number) {
      return '#' + this.componentToHex(r) + this.componentToHex(g) + this.componentToHex(b);
  }

  mainLoop() {
      // vasClear the can
      this.ctx.clearRect(0, 0, this.width, this.height);
      // For each ball in the array
      for(let i=0; i < this.ballArray.length; i++) {
        const balls = this.ballArray[i];

        // 1) Move the ball
        balls.move();

        // 2) collision test with walls
        this.collisionTestWithWalls(balls);

        // 3) draw the ball
        balls.draw();
    }

    this.collisionTestBetweenBalls();

    // Ask for new animation frame
    window.requestAnimationFrame(() => this.mainLoop());
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
    const balls = this.ballArray;

    for (let i = 0; i < this.ballArray.length; i++) {
          for (let j = i + 1; j < this.ballArray.length; j++) {
              const dx = balls[j].x - balls[i].x;
              const dy = balls[j].y - balls[i].y;

              const dist = Math.sqrt(dx * dx + dy * dy);
              if (dist < (balls[j].radius + balls[i].radius)) {
                  // balls have contact so push back...
                  const normalX = dx / dist;
                  const normalY = dy / dist;
                  const middleX = (balls[i].x + balls[j].x) / 2;
                  const middleY = (balls[i].y + balls[j].y) / 2;

                  balls[i].x = middleX - normalX * balls[i].radius;
                  balls[i].y = middleY - normalY * balls[i].radius;
                  balls[j].x = middleX + normalX * balls[j].radius;
                  balls[j].y = middleY + normalY * balls[j].radius;

                  let dVector = (balls[i].vx - balls[j].vx) * normalX;
                  dVector += (balls[i].vy - balls[j].vy) * normalY;
                  const dvx = dVector * normalX;
                  const dvy = dVector * normalY;

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

    constructor(public x: number, public y: number, public vx: number, public vy: number, diameter: number, private ctx: CanvasRenderingContext2D, private color: string) {
        this.radius = diameter/2;
    }

    draw() {
      this.ctx.beginPath();
      this.ctx.fillStyle = this.color;
      this.ctx.arc(this.x, this.y, this.radius, 0, 2*Math.PI);
      this.ctx.fill();
    };

    move() {
      this.x += this.vx;
      this.y += this.vy;
    };

  }
