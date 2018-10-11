import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { VaultSummary } from "../models/vault-summary.model";
import { MessageService } from "./message.service";
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class VaultSummaryReportService {
  private vaultSummaryReportUrl = environment.apiUrl + '/v1/cleversafereports/vaultsummary';

  constructor(private httpClient: HttpClient, private messageService: MessageService) { }

  getVaultSummaryReport(): Observable<VaultSummary[]> {
    //this.log('Getting Vault Summary Report Data...');
    return this.httpClient.get<VaultSummary[]>(this.vaultSummaryReportUrl)
  }

  private log(message: string) {
    this.messageService.add(message);
  }
}
