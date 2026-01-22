import java.sql.*;

public class Query {
    private Connection conn;

    public void Connessione(String url, String user, String pass) throws SQLException {
        this.conn = DriverManager.getConnection(url, user, pass);
        System.out.println("Connessione stabilita con successo.");
    }

    // 1. Registrazione di una nuova prenotazione per un alloggio
    public void registraPrenotazione(int id_utente, int id_alloggio, Date data_inizio, Date data_fine, int ospiti, double sconto) throws SQLException {
        String sql = "INSERT INTO Prenotazione (Costo_Totale, Sconto, Data_Inizio, Data_Fine, Ospiti, ID_Utente, ID_Alloggio) " +
                     "VALUES ((DATEDIFF(?, ?) * (SELECT Costo_Notte FROM Alloggio WHERE ID_Alloggio = ?) * (1 - ?/100)), ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, data_fine); ps.setDate(2, data_inizio); ps.setInt(3, id_alloggio); ps.setDouble(4, sconto);
            ps.setDouble(5, sconto); ps.setDate(6, data_inizio); ps.setDate(7, data_fine);
            ps.setInt(8, ospiti); ps.setInt(9, id_utente); ps.setInt(10, id_alloggio);
            ps.executeUpdate();
            System.out.println("Prenotazione registrata!");
        }
    }

    // 2. Prenotazione di una esperienza
    public void prenotaEsperienza(int id_utente, int id_esperienza, Date data_svolgimento) throws SQLException {
        String sql = "INSERT INTO Acquisto_Esperienza (ID_Utente, ID_Esperienza, Data_svolgimento) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id_utente); ps.setInt(2, id_esperienza); ps.setDate(3, data_svolgimento);
            ps.executeUpdate();
            System.out.println("Esperienza prenotata con successo!");
        }
    }

    // 3. Aggiunta di un nuovo alloggio
    public void aggiungiAlloggio(String nome, double metri_quadri, String via, int n_civico, String descrizione, double costo_notte, int max_ospiti, String tipologia, Boolean bagno_privato, Boolean genere_specifico, Integer n_locali, Integer n_piani, Boolean giardino, Boolean ut_comp, Boolean animali, String link, int id_citta, int id_host) throws SQLException {
        String sql = "INSERT INTO Alloggio (Nome, Descrizione, Metri_Quadri, Via, N_Civico, Max_Ospiti, Costo_Notte, Tipologia, Utenze_Comprese, Animali, Link, ID_Città, ID_Host, Bagno_Privato, Genere_Specifico, Locali, Piani, Giardino) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nome); ps.setString(2, descrizione); ps.setDouble(3, metri_quadri); ps.setString(4, via); ps.setInt(5, n_civico);
            ps.setInt(6, max_ospiti); ps.setDouble(7, costo_notte); ps.setString(8, tipologia);
            ps.setObject(9, ut_comp); ps.setObject(10, animali); ps.setString(11, link); ps.setInt(12, id_citta); ps.setInt(13, id_host);
            ps.setObject(14, bagno_privato); ps.setObject(15, genere_specifico); ps.setObject(16, n_locali); ps.setObject(17, n_piani); ps.setObject(18, giardino);
            ps.executeUpdate();
            System.out.println("Alloggio inserito correttamente.");
        }
    }

	// 4. Aggiunta di un servizio per uno specifico alloggio
    public void aggiungiServizio(String nome_servizio, int id_alloggio) throws SQLException {
        String sql = "INSERT INTO Offrire_Servizio (Nome_Servizio, ID_Alloggio) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nome_servizio); ps.setInt(2, id_alloggio);
            ps.executeUpdate();
            System.out.println("Servizio aggiunto.");
        }
    }

	// 5. Modifica del numero di locali di un appartamento
    public void modificaLocali(int id_alloggio, int n_locali) throws SQLException {
        String sql = "UPDATE Alloggio SET Locali = ? WHERE ID_Alloggio = ? AND Tipologia = 'Appartamento'";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, n_locali); ps.setInt(2, id_alloggio);
            ps.executeUpdate();
            System.out.println("Locali aggiornati.");
        }
    }

	// 6. Stampa di tutte le prenotazioni registrate, compreso l’importo totale
    public void stampaPrenotazioni() throws SQLException {
        String sql = "SELECT ID_Prenotazione, Costo_Totale, Data_Inizio, Data_Fine FROM Prenotazione";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) System.out.println("ID Prenotazione: " + rs.getInt(1) + " | Costo Totale: " + rs.getDouble(2) + " | Dal: " + rs.getDate(3) + " Al: " + rs.getDate(4));
        }
    }

	// 7. Stampa i dati dei professionisti che lavorano presso una specifica agenzia
    public void professionistiAgenzia(int id_agenzia) throws SQLException {
        String sql = "SELECT U.ID_Utente, U.Nome, U.Cognome FROM Utente U JOIN Host_ H ON U.ID_Utente = H.ID_Utente WHERE H.ID_Agenzia = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id_agenzia);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) System.out.println("ID Host: " + rs.getInt(1) + " | Nome e Cognome: " + rs.getString(2) + " " + rs.getString(3));
        }
    }

	// 8. Stampa di tutti gli utenti, compreso il numero di prenotazioni effettuate
    public void utentiPrenotazioni() throws SQLException {
        String sql = "SELECT U.ID_Utente, U.Nome, U.Cognome, COUNT(*) FROM Utente U JOIN Prenotazione P ON U.ID_Utente = P.ID_Utente GROUP BY U.ID_Utente";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) System.out.println("ID Utente: " + rs.getInt(1) + " | Nome e Cognome: " + rs.getString(2) + " " + rs.getString(3) + " | N. Prenotazioni: " + rs.getInt(4));
        }
    }

	// 9. Stampa del numero di alloggi gestiti da ogni host
    public void alloggiPerHost() throws SQLException {
        String sql = "SELECT ID_Host, COUNT(*) FROM Alloggio GROUP BY ID_Host";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) System.out.println("ID Host: " + rs.getInt(1) + " | N. Alloggi gestiti: " + rs.getInt(2));
        }
    }

	// 10.Ricerca degli alloggi disponibili in un determinato range di date che possano accogliere almeno n ospiti
    public void cercaDisponibili(Date d1, Date d2, int n) throws SQLException {
        String sql = "SELECT ID_Alloggio, Nome FROM Alloggio WHERE Max_Ospiti >= ? AND ID_Alloggio NOT IN " +
                     "(SELECT ID_Alloggio FROM Prenotazione WHERE NOT (Data_Fine <= ? OR Data_Inizio >= ?))";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, n); ps.setDate(2, d1); ps.setDate(3, d2);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) System.out.println("Alloggio disponibile - ID: " + rs.getInt(1) + " | Nome: " + rs.getString(2));
        }
    }

	// 11. Stampa di tutti gli utenti che hanno effettuato prenotazioni per un totale di almeno 30 giorni
    public void utentiFedeli() throws SQLException {
        String sql = "SELECT U.ID_Utente, U.Nome, U.Cognome, SUM(DATEDIFF(P.Data_Fine, P.Data_Inizio)) FROM Utente U JOIN Prenotazione P ON U.ID_Utente = P.ID_Utente GROUP BY U.ID_Utente HAVING SUM(DATEDIFF(P.Data_Fine, P.Data_Inizio)) >= 30";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) System.out.println("ID Utente: " + rs.getInt(1) + " | Nome e Cognome: " + rs.getString(2) + " " + rs.getString(3) + " | N. Giorni: " + rs.getInt(4));
        }
    }

	// 12. Stampa del rating medio di ciascun host per ognuno dei 4 rating delle recensioni
	public void ratingMedioHost() throws SQLException {
		String sql = "SELECT A.ID_Host, AVG(R.Qualità), AVG(R.Precisione), AVG(R.Disponibilità), AVG(R.Pulizia) FROM Alloggio A JOIN Prenotazione P ON A.ID_Alloggio = P.ID_Alloggio JOIN Recensione R ON P.ID_Prenotazione = R.ID_Prenotazione GROUP BY A.ID_Host";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("ID Host: " + rs.getInt(1) + 
                                   " | Qualità: " + String.format("%.2f", rs.getDouble(2)) + 
                                   " | Precisione: " + String.format("%.2f", rs.getDouble(3)) + 
                                   " | Disponibilità: " + String.format("%.2f", rs.getDouble(4)) + 
                                   " | Pulizia: " + String.format("%.2f", rs.getDouble(5)));
            }
        }
    }

	// 13. Stampa di una classifica degli host ordinata in base al numero totale di recensioni ricevute
    public void classificaHost() throws SQLException {
        String sql = "SELECT A.ID_Host, U.Nome, U.Cognome, COUNT(R.ID_Prenotazione) as NRec FROM Alloggio A JOIN Prenotazione P ON A.ID_Alloggio = P.ID_Alloggio JOIN Recensione R ON P.ID_Prenotazione = R.ID_Prenotazione JOIN Utente U ON A.ID_Host = U.ID_Utente GROUP BY A.ID_Host, U.Nome, U.Cognome ORDER BY NRec DESC";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) System.out.println("ID Host: " + rs.getInt(1) + " | Nome e Cognome: " + rs.getString(2) + " " + rs.getString(3) + " | N. Recensioni ricevute: " + rs.getInt(4));
        }
    }

	// 14. Stampa Nome e Descrizione degli alloggi che hanno ospitato (in totale) almeno 30 ospiti
    public void alloggiPopolari() throws SQLException {
        String sql = "SELECT A.ID_Alloggio, A.Nome, A.Descrizione, SUM(P.Ospiti) as TotOspiti FROM Alloggio A JOIN Prenotazione P ON A.ID_Alloggio = P.ID_Alloggio GROUP BY A.ID_Alloggio, A.Nome, A.Descrizione HAVING TotOspiti >= 30";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) System.out.println("ID Alloggio: " + rs.getInt(1) + " | Nome: " + rs.getString(2) + " | Descrizione: " + rs.getString(3) + " | Tot. Ospiti: " + rs.getInt(4));
        }
    }

	// 15. Stampa di tutti gli utenti che non hanno prenotato alcuna esperienza
    public void utentiSenzaEsperienze() throws SQLException {
        String sql = "SELECT U.ID_Utente, U.Nome, U.Cognome FROM Utente U LEFT JOIN Acquisto_Esperienza A ON U.ID_Utente = A.ID_Utente WHERE A.ID_Esperienza IS NULL";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) System.out.println("ID Utente: " + rs.getInt(1) + " | Nome e Cognome: " + rs.getString(2) + " " + rs.getString(3));
        }
    }
}