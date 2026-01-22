import java.util.Scanner;
import java.sql.Date;

public class App {
    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3306/ProgettoBD?serverTimezone=UTC"; 
        String root = "root";
        String pass = "admin"; 
        
        Query query = new Query();
        int scelta;
        Scanner sc = new Scanner(System.in);

        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
            query.Connessione(url, root, pass);
        } catch (Exception e) {
            System.out.println("Errore di connessione: " + e.getMessage());
            return; 
        }

        do {
            System.out.println("\n");
            System.out.println("Quale operazione effettuare? (-1 per uscire)");
            System.out.println();
            
            System.out.println("1 - Registrazione di una nuova prenotazione per un alloggio\n");
            System.out.println("2 - Prenotazione di una nuova esperienza\n");
            System.out.println("3 - Aggiunta di un nuovo alloggio\n");
            System.out.println("4 - Aggiunta di un servizio per uno specifico alloggio\n");
            System.out.println("5 - Modifica del numero di locali di un appartamento\n");
            System.out.println("6 - Stampa di tutte le prenotazioni registrate, compreso l'importo totale\n");
            System.out.println("7 - Stampa i dati dei professionisti che lavorano presso una specifica agenzia\n");
            System.out.println("8 - Stampa di tutti gli utenti, compreso il numero di prenotazioni effettuate\n");
            System.out.println("9 - Stampa del numero di alloggi gestiti da ogni host\n");
            System.out.println("10 - Ricerca degli alloggi disponibili in un determinato range di date\n");
            System.out.println("11 - Stampa di tutti gli utenti che hanno prenotato per almeno 30 giorni\n");
            System.out.println("12 - Stampa del rating medio di ciascun host per categoria\n");
            System.out.println("13 - Stampa di una classifica degli host per numero di recensioni\n");
            System.out.println("14 - Stampa Nome e Descrizione degli alloggi con almeno 30 ospiti totali\n");
            System.out.println("15 - Stampa di tutti gli utenti che non hanno prenotato alcuna esperienza\n");
            
            System.out.print("Scelta: ");
            scelta = sc.nextInt();

            try {
                switch (scelta) {
                    case 1:
                        System.out.print("ID Utente: ");
                        int id_utente = sc.nextInt();

                        System.out.print("ID Alloggio: ");
                        int id_alloggio = sc.nextInt();

                        System.out.print("Data inizio (AAAA-MM-DD): ");
                        String startP = sc.next();

                        System.out.print("Data fine (AAAA-MM-DD): ");
                        String endP = sc.next();

                        System.out.print("N. ospiti: ");
                        int ospiti = sc.nextInt();

                        System.out.print("Sconto (%): ");
                        double sconto = sc.nextDouble();

                        query.registraPrenotazione(id_utente, id_alloggio, Date.valueOf(startP), Date.valueOf(endP), ospiti, sconto);
                        break;

                    case 2:
                        System.out.print("ID Utente: ");
                        int id_u2 = sc.nextInt();

                        System.out.print("ID Esperienza: ");
                        int id_e2 = sc.nextInt();

                        System.out.print("Data svolgimento (AAAA-MM-DD): ");
                        String data_e2 = sc.next();

                        query.prenotaEsperienza(id_u2, id_e2, Date.valueOf(data_e2));
                        break;

                    case 3:
                        sc.nextLine(); 

                        System.out.print("Nome alloggio: ");
                        String nome_all = sc.nextLine();

                        System.out.print("Metri quadri: ");
                        double mq = sc.nextDouble();

                        sc.nextLine(); 

                        System.out.print("Via: ");
                        String via = sc.nextLine();

                        System.out.print("N. Civico: ");
                        int civico = sc.nextInt();

                        sc.nextLine(); 

                        System.out.print("Descrizione: ");
                        String desc = sc.nextLine();

                        System.out.print("Costo per notte: ");
                        double costo = sc.nextDouble();

                        System.out.print("Massimo ospiti: ");
                        int max_o = sc.nextInt();

                        System.out.print("Tipologia (camera [singola]/appartamento/villa): ");
                        String tipo = sc.next().toLowerCase();

                        Boolean bagno = null, genere = null, giardino = null; 
                        Integer loc = null, piani = null;

                        if (tipo.contains("camera")) {
                            System.out.print("Bagno privato? (y/n): ");
                            bagno = sc.next().equalsIgnoreCase("y");
                            System.out.print("Genere specifico? (y/n): ");
                            genere = sc.next().equalsIgnoreCase("y");
                        } else if (tipo.contains("appartamento")) {
                            System.out.print("N. locali: ");
                            loc = sc.nextInt();
                        } else if (tipo.contains("villa")) {
                            System.out.print("N. piani: ");
                            piani = sc.nextInt();
                            System.out.print("Giardino? (y/n): ");
                            giardino = sc.next().equalsIgnoreCase("y");
                        }

                        System.out.print("Utenze comprese? (y/n): ");
                        Boolean ut = sc.next().equalsIgnoreCase("y");

                        System.out.print("Animali ammessi? (y/n): ");
                        Boolean anim = sc.next().equalsIgnoreCase("y");

                        System.out.print("Link alloggio: ");
                        String link = sc.next();

                        System.out.print("ID Città: ");
                        int id_c = sc.nextInt();

                        System.out.print("ID Host: ");
                        int id_h = sc.nextInt();

                        query.aggiungiAlloggio(nome_all, mq, via, civico, desc, costo, max_o, tipo, bagno, genere, loc, piani, giardino, ut, anim, link, id_c, id_h);
                        break;

                    case 4:
						sc.nextLine();
						
                        System.out.print("Nome servizio: ");
                        String serv = sc.nextLine();

                        System.out.print("ID Alloggio: ");
                        int id_a4 = sc.nextInt();

                        query.aggiungiServizio(serv, id_a4);
                        break;

                    case 5:
                        System.out.print("ID Alloggio: ");
                        int id5 = sc.nextInt();

                        System.out.print("N. locali: ");
                        int loc5 = sc.nextInt();

                        query.modificaLocali(id5, loc5);
                        break;

                    case 6:
                        query.stampaPrenotazioni();
                        break;

                    case 7:
                        System.out.print("ID Agenzia: ");
                        int id7 = sc.nextInt();
                        query.professionistiAgenzia(id7);
                        break;

                    case 8:
                        query.utentiPrenotazioni();
                        break;

                    case 9:
                        query.alloggiPerHost();
                        break;

                    case 10:
                        System.out.print("Inizio (AAAA-MM-DD): ");
                        String start = sc.next();
                        
                        System.out.print("Fine (AAAA-MM-DD): ");
                        String end = sc.next();
                        
                        System.out.print("N. ospiti: ");
                        int o10 = sc.nextInt();

                        query.cercaDisponibili(Date.valueOf(start), Date.valueOf(end), o10);
                        break;

                    case 11:
                        query.utentiFedeli();
                        break;

                    case 12:
                        query.ratingMedioHost();
                        break;

                    case 13:
                        query.classificaHost();
                        break;

                    case 14:
                        query.alloggiPopolari();
                        break;

                    case 15:
                        query.utentiSenzaEsperienze();
                        break;
                }
            } catch (Exception e) {
                System.out.println("Errore: " + e.getMessage());
            }
        } while (scelta != -1);
        sc.close();
    }
}