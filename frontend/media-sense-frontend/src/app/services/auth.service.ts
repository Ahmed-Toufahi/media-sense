import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { catchError, tap, map } from 'rxjs/operators';
import { Router } from '@angular/router';

export interface AuthResponse {
  token: string;
}

export interface AuthRequest {
  username: string; 
  password: string;
}


export interface UserDTO {
  username: string; // Email
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080'; 
  private tokenKey = 'auth_token';
  private currentUserSubject = new BehaviorSubject<UserDTO | null>(null);
  public currentUser$ = this.currentUserSubject.asObservable();
  
  constructor(
    private http: HttpClient,
    private router: Router
  ) {
    this.loadCurrentUser();
  }

  register(email: string, password: string): Observable<AuthResponse> {
    const registerRequest: AuthRequest = {
      username: email, 
      password: password,
    };
    
    return this.http.post<AuthResponse>(`${this.apiUrl}/users/register`, registerRequest)
      .pipe(
        tap(response => this.handleAuthentication(response.token)),
        catchError(this.handleError)
      );
  }

  login(email: string, password: string): Observable<AuthResponse> {
    const authRequest: AuthRequest = {
      username: email, 
      password: password
    };
    
    return this.http.post<AuthResponse>(`${this.apiUrl}/auth/login`, authRequest)
      .pipe(
        tap(response => this.handleAuthentication(response.token)),
        catchError(this.handleError)
      );
  }

  logout(): void {
    localStorage.removeItem(this.tokenKey);
    this.currentUserSubject.next(null);
    this.router.navigate(['/login']);
  }

  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  isAuthenticated(): boolean {
    const token = this.getToken();
    return !!token;
  }

  getUserProfile(): Observable<UserDTO> {
    return this.http.get<UserDTO>(`${this.apiUrl}/users/me`, {
      headers: this.getAuthHeaders()
    }).pipe(
      tap(user => this.currentUserSubject.next(user)),
      catchError(this.handleError)
    );
  }

  getAuthHeaders(): HttpHeaders {
    const token = this.getToken();
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    let errorMessage = 'An unknown error occurred.';
  
    if (error.error && typeof error.error === 'object') {
      errorMessage = error.error.message || error.message;
    }
  
    console.error('HTTP Error:', error);
    return throwError(() => new Error(errorMessage));
  }

  private handleAuthentication(token: string): void {
    localStorage.setItem(this.tokenKey, token);
    this.getUserProfile().subscribe();
    console.log('User profile loaded successfully');
  }

  private loadCurrentUser(): void {
    const token = this.getToken();
    if (token) {
      this.getUserProfile().subscribe();
    }
  }
}