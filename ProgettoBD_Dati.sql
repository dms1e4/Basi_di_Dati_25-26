USE ProgettoBD;

INSERT INTO Utente (Nome, Cognome, Email, Nazionalità, Biografia, ID_Utente) VALUES
('Mario', 'Rossi', 'mrossi@gmail.com', 'Italiano', 'Sono Mario Rossi, la mia mail è mrossi@gmail.com!', 1),
('John', 'Smith', 'jsmith@apple.com', 'Inglese', NULL, 2),
('Giacomo', 'Verdi', 'giac.verdi@libero.it', 'Italiano', 'Sono Giacomo.', 3),
('Emma', 'Karl', 'ekarl2@web.de', 'Tedesco', 'Hallo, ich bin Emma!', 4),
('Paul', 'Petit', 'papet@mailo.fr', 'Francese', 'Bonjour!', 5),
('Eric', 'Alonso', 'aleric@mail.es', 'Spagnolo', NULL, 6);

INSERT INTO Agenzia (Nome, Sede, ID_Agenzia) VALUES
('ItalTour', 'Napoli', 1),
('FreeTravel', 'Roma', 2),
('ViaggiaEuropa', 'Milano', 3),
('EuroTravel', 'Berlino', 4),
('LondonTrip', 'Londra', 5);

INSERT INTO Città (Nome, Regione, Nazione, ID_Città) VALUES
('Napoli', 'Campania','Italia', 1),
('Roma', 'Lazio','Italia', 2),
('Milano', 'Lombardia','Italia', 3),
('Londra', NULL, 'Inghilterra', 4),
('Berlino', NULL, 'Germania', 5),
('Barcellona', 'Catalogna', 'Spagna', 6);

INSERT INTO Servizio (Nome, Tipologia) VALUES
('Wi-Fi gratuito', 'Connettività'),
('Riscaldamento', 'Comfort'),
('Smart TV', 'Comfort'),
('Ascensore', 'Comfort'),
('Microonde', 'Elettrodomestici'),
('Asciugacapelli', 'Elettrodomestici'),
('Aria Condizionata', 'Comfort'),
('Cucina attrezzata', 'Elettrodomestici');

INSERT INTO Telefono_Utente (Numero, ID_Utente) VALUES
('+393948375591', 1),
('+4407963093919', 2),
('+393327563349', 3),
('+49305748596', 4),
('+33780763733', 5),
('+34628123662', 6);

INSERT INTO Host_(ID_Utente, Tasso_Risposta, Tipo_Host, Data_Ottenimento, Essere_Superhost, ID_Agenzia) VALUES
(1, 1, 'Privato', '2025-11-28', TRUE, NULL),
(2, 3, 'Professionista', NULL, FALSE, 3),
(3, 5, 'Privato', '2025-11-25', TRUE, NULL),
(6, 2, 'Professionista', NULL, FALSE, 5);

INSERT INTO Contatto_Agenzia (Contatto, ID_Agenzia) VALUES
('+390818474621', 1),
('+39068673216', 2),
('+39026847552', 3),
('+49301234567', 4),
('+44207123456', 5);

INSERT INTO Punto_Di_Interesse (Punto, ID_Città) VALUES
('Stadio Diego Armando Maradona', 1),
('Colosseo', 2),
('Duomo', 3),
('Big Ben', 4),
('Porta di Brandeburgo', 5),
('Sagrada Familia', 6);

INSERT INTO Esperienza (ID_Esperienza, Nome, Prezzo, Descrizione, Max_Partecipanti, ID_Agenzia) VALUES
(1, 'Escursione sul lungomare', 15, 'Escursione guidata con guida professionista nel cuore di Napoli', 10, 1),
(2, 'Gita al Colosseo', 20, 'Escursione guidata con guida locale', 15, 2),
(3, 'Scala di Milano', 30, 'Ingresso nel Teatro alla Scala di Milano, prezzo del biglietto incluso', 10, 3),
(4, 'Musei Capitolini', 30, 'Ingresso ai Musei Capitolini, prezzo biglietto escluso', 10, 2),
(5, 'Visita al murales di Maradona', 10, 'Escursione con guida locale al murales di un idolo', 20, 1),
(6, 'London Eye', 45, 'Giro panoramico sulla ruota di Londra', 25, 5),
(7, 'Muro di Berlino', 15, 'Camminata guidata lungo il Muro di Berlino', 20, 4);

INSERT INTO Lingua_Host (Lingua, ID_Host) VALUES
('Italiano', 1),
('Inglese', 2),
('Italiano', 3),
('Spagnolo', 6);

INSERT INTO Badge (Categoria, ID_Host) VALUES
('Pulizia', 1),
('Precisione', 3);

INSERT INTO Alloggio (ID_Alloggio, ID_Host, ID_Città, Link, Nome, Descrizione, Metri_Quadri, Via, N_Civico, Max_Ospiti, Costo_Notte, Tipologia, Utenze_Comprese, Bagno_Privato, Genere_Specifico, Animali, Locali, Giardino, Piani) VALUES
(1, 1, 1, 'allogginapoli.it/appartamento_in_centro', 'Appartamento In Centro', 'Bilocale nel centro di Napoli', 50, 'Via Toledo', 83, 3, 120, 'Appartamento', TRUE, TRUE, FALSE, TRUE, 2, NULL, NULL),
(2, 2, 2, 'viaggiaroma.it/villa_periferica', 'Villa distaccata', 'Comfortevole villa nella periferia romana', 87, 'Via Adami', 18, 6, 70, 'Villa', FALSE, TRUE, FALSE, FALSE, NULL, TRUE, 2),
(3, 3, 3, 'milanovisit.it/camera_centro', 'Camera centrale', 'Camera singola in pieno centro', 20, 'Via Adda', 2, 1, 100, 'Camera Singola', FALSE, TRUE, FALSE, FALSE, 1, NULL, NULL),
(4, 6, 4, 'londontrip.uk/modern_flat', 'Appartamento a Soho', 'Appartamento moderno nel cuore di Londra', 60, 'Dean St.', 12, 4, 250, 'Appartamento', TRUE, TRUE, FALSE, FALSE, 3, NULL, NULL),
(5, 6, 6, 'barcastay.es/casa_gracia', 'Studio Gracia', 'Monolocale luminoso nel quartiere Gracia', 35, 'Carrer Gran', 5, 2, 90, 'Appartamento', TRUE, TRUE, FALSE, TRUE, 1, NULL, NULL);

INSERT INTO Acquisto_Esperienza (ID_Utente, ID_Esperienza, Data_svolgimento) VALUES
(4, 1, '2025-12-02'),
(5, 2, '2025-12-13'),
(3, 6, '2025-12-20'),
(1, 7, '2025-12-22');

INSERT INTO Offrire_Servizio (Nome_Servizio, ID_Alloggio) VALUES
('Smart TV', 1),
('Wi-Fi gratuito', 2),
('Riscaldamento', 3),
('Wi-Fi gratuito', 4),
('Microonde', 5),
('Riscaldamento', 1),
('Aria Condizionata', 4),
('Cucina attrezzata', 3);

INSERT INTO Prenotazione (ID_Prenotazione, ID_Utente, ID_Alloggio, Costo_Totale, Sconto, Data_Inizio, Data_Fine, Ospiti) VALUES
(1, 4, 1, 230, 10, '2025-11-28', '2025-11-30', 2),
(2, 5, 2, 195, 15, '2025-12-01', '2025-12-04', 4),
(3, 3, 4, 750, 0, '2025-12-19', '2025-12-22', 2),
(4, 4, 5, 180, 0, '2026-01-10', '2026-01-12', 2),
(5, 2, 3, 1900, 0, '2026-03-01', '2026-03-20', 1),
(6, 2, 3, 1400, 0, '2026-04-01', '2026-04-15', 1),
(7, 1, 2, 140, 0, '2026-05-01', '2026-05-03', 6),
(8, 6, 2, 140, 0, '2026-05-05', '2026-05-07', 6),
(9, 3, 2, 140, 0, '2026-05-10', '2026-05-12', 6),
(10, 4, 2, 140, 0, '2026-05-15', '2026-05-17', 6),
(11, 5, 2, 140, 0, '2026-05-20', '2026-05-22', 6);

INSERT INTO Recensione (ID_Prenotazione, Descrizione, Data_, Qualità, Precisione, Disponibilità, Pulizia) VALUES
(1, 'Io e mio marito siamo stati bene, torneremo sicuramente!', '2025-12-01', 3, 5, 4, 5),
(2, 'Il viaggio in famiglia è stato ottimo, da rivedere la pulizia', '2025-12-06', 4, 4, 5, 3),
(4, 'Piccolo ma molto accogliente. Eric super disponibile.', '2026-01-13', 4, 4, 5, 5);

