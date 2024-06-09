import {Component} from '@angular/core';
import {ActivatedRoute, Router, RouterLink} from "@angular/router";
import {AccessorService} from "../service/accessor-service";
import {AsyncPipe, NgClass, NgIf} from "@angular/common";

@Component({
  selector: 'app-risk-aside',
  standalone: true,
  imports: [
    RouterLink,
    AsyncPipe,
    NgIf,
    NgClass
  ],
  templateUrl: './risk-aside.component.html',
  styleUrl: './risk-aside.component.scss'
})
export class RiskAsideComponent {

  id!: string;
  risk!: string;
  none!: boolean;
  borderline!: boolean;
  inDanger!: boolean;
  earlyOnset!: boolean;


  constructor(private service: AccessorService, private route: ActivatedRoute, private router: Router) {

    this.id = location.pathname.split('/').slice(-1)[0];
    if (router.url != '/' && router.url != '/new/patient') {

      this.service.getRisk(this.id).subscribe(value => {
        this.risk = value.result;
        if (this.risk === "none") {
          this.none = true;
          this.borderline = false;
          this.inDanger = false;
          this.earlyOnset = false;
        } else if (this.risk === "borderline") {
          this.none = false;
          this.borderline = true;
          this.inDanger = false;
          this.earlyOnset = false;
        } else if (this.risk === "indanger") {
          this.none = false;
          this.borderline = false;
          this.inDanger = true;
          this.earlyOnset = false;
        } else if (this.risk === "earlyonset") {
          this.none = false;
          this.borderline = false;
          this.inDanger = false;
          this.earlyOnset = true;
        }
        console.log(this.risk)
        console.log("none " + this.none)
        console.log("borderline " + this.borderline)
        console.log("indanger " + this.inDanger)
        console.log("earlyOnset " + this.earlyOnset)
      });
    } else {
      this.risk = 'undefined';
    }
  }

}
