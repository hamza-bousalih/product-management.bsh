import {Component, inject} from '@angular/core';
import {NgStyle} from '@angular/common';
import {IconDirective} from '@coreui/icons-angular';
import {
    ButtonDirective,
    CardBodyComponent,
    CardComponent,
    CardGroupComponent,
    ColComponent,
    ContainerComponent,
    FormControlDirective,
    FormDirective,
    InputGroupComponent,
    InputGroupTextDirective,
    RowComponent,
    SpinnerComponent,
    TextColorDirective
} from '@coreui/angular';
import {AuthService} from "src/app/controller/auth/services/auth.service";
import {Router, RouterLink} from "@angular/router";
import {TokenService} from "src/app/controller/auth/services/token.service";
import {FormsModule} from "@angular/forms";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss'],
    standalone: true,
    imports: [
        ContainerComponent,
        RowComponent,
        ColComponent,
        CardGroupComponent,
        TextColorDirective,
        CardComponent,
        CardBodyComponent,
        FormDirective,
        InputGroupComponent,
        InputGroupTextDirective,
        IconDirective,
        FormControlDirective,
        ButtonDirective,
        NgStyle,
        FormsModule,
        SpinnerComponent,
        RouterLink
    ]
})
export class LoginComponent {

    loading = false

    private userService = inject(AuthService)
    private router = inject(Router)
    private authService = inject(TokenService)


    get login() {
        return this.userService.jwtRequest;
    }

    submit() {
        this.loading = true;
        this.userService.login().subscribe({
            next: data => {
                console.log(data)
                this.authService.setToken(data.accessToken)
                this.router.navigate(["dashboard"]).then()
                this.loading = false
            },
            error: err => {
                console.log(err)
                this.loading = false;
            }
        })
    }

}
