

INSERT INTO typefinancement (nom_financement) VALUES
                                                  ('Credit a la consomation'),
                                                  ('Credit immobilier'),
                                                  ('Prêt sur gage'),
                                                  ('Ijara Tamlikia'),
                                                  ('Morabaha véhicule'),
                                                  ('Morabaha moto'),
                                                  ('Morabaha Istihlakiya');

INSERT INTO typecredit (nom_credit, id_financement) VALUES
                                                        ('Produit électronique et électroménager', 1),
                                                        ('Meubles et accessoires de maison', 1),
                                                        ('Véhicule à usage touristique', 1);


INSERT INTO typecredit (nom_credit, id_financement) VALUES
                                                        ('Achat d''un logement promotionnel achevé ou vendu sur plans', 2),
                                                        ('Construction d''un logement rural', 2),
                                                        ('Achat d''un logement LPP (Bonifié)', 2),
                                                        ('Achat d''un logement auprès d''un particulier', 2),
                                                        ('Achat d''un logement auprès d''un promoteur', 2),
                                                        ('Achat d''un logement formule "Vente Sur Plan"', 2),
                                                        ('Acquisition d''un local commercial en "VSP"', 2),
                                                        ('Coopérative immobilière', 2),
                                                        ('Aménagement', 2),
                                                        ('Construction d''une habitation individuelle', 2),
                                                        ('Construction zone rurale', 2),
                                                        ('Extension d''une habitation individuelle', 2);




-- Inserting the first three wilayas into the Wilaya table
INSERT INTO Wilaya (wilaya_name) VALUES ('Tizi Ouzou');
INSERT INTO Wilaya (wilaya_name) VALUES ('Alger');
INSERT INTO Wilaya (wilaya_name) VALUES ('Boumerdes');

-- Inserting communes into commune tizi ouzou
INSERT INTO Commune (nom, code_postal, wilaya_id) VALUES ('Tizi Ouzou', '15000', 1);
INSERT INTO Commune (nom, code_postal, wilaya_id) VALUES ('Draa El Mizan', '15005', 1);
INSERT INTO Commune (nom, code_postal, wilaya_id) VALUES ('Tizi Gheniff', '15020', 1);
INSERT INTO Commune (nom, code_postal, wilaya_id) VALUES ('Freha', '15012', 1);
INSERT INTO Commune (nom, code_postal, wilaya_id) VALUES ('Azazga', '15001', 1);
INSERT INTO Commune (nom, code_postal, wilaya_id) VALUES ('Aïn El Hammam', '15002', 1);
INSERT INTO Commune (nom, code_postal, wilaya_id) VALUES ('Larbaâ Nath Irathen', '15006', 1);

INSERT INTO Commune (nom, code_postal, wilaya_id) VALUES ('Azeffoun', '15010', 1);
INSERT INTO Commune (nom, code_postal, wilaya_id) VALUES ('Draâ Ben Khedda', '15004', 1);
INSERT INTO Commune (nom, code_postal, wilaya_id) VALUES ('Tadmait', '15018', 1);
INSERT INTO Commune (nom, code_postal, wilaya_id) VALUES ('Ouaguenoun', '15047', 1);
INSERT INTO Commune (nom, code_postal, wilaya_id) VALUES ('Tigzirt', '15019', 1);
INSERT INTO Commune (nom, code_postal, wilaya_id) VALUES ('Mekla', '15014', 1);
INSERT INTO Commune (nom, code_postal, wilaya_id) VALUES ('Boghni','15003', 1);
INSERT INTO Commune (nom, code_postal, wilaya_id) VALUES ('AGHRIB','15021', 1);

INSERT INTO direction_regionale (nom, created_at, adresse) VALUES ('Direction Tizi Ouzou', CURRENT_TIMESTAMP, 'Axe du Nouveau Lycée, Boulevard Stitti,Tizi Ouzou');
INSERT INTO direction_regionale (nom, created_at, adresse) VALUES ('Direction Alger', CURRENT_TIMESTAMP, 'Address for Alger direction');

INSERT INTO Agence (num_agence, nom, adresse, direction_regionale_id, created_at, updated_at)
VALUES ('138', 'AGENCE BOGHNI', 'Cité 18 Logements Boghni', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO Agence (num_agence, nom, adresse, direction_regionale_id, created_at, updated_at)
VALUES ('144', 'AGENCE TIGZIRT', 'CITE 192 LOGEMENTS BATIMENT B1 15019 TIGZIRT TIZI-OUZOU', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO Agence (num_agence, nom, adresse, direction_regionale_id, created_at, updated_at)
VALUES ('145', 'AGENCE FREHA', 'CITE 18 LOGEMENTS 15012 FREHA TIZI-OUZOU', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO Agence (num_agence, nom, adresse, direction_regionale_id, created_at, updated_at)
VALUES ('147', 'Pôle Commercial DJURDJURA', 'Axe Nouveux Lycée, Tizi Ouzou', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO Agence (num_agence, nom, adresse, direction_regionale_id, created_at, updated_at)
VALUES ('151', 'Pôle Commercial AZAZGA', 'RUE BELKACEM HANAFI 15001 AZAZGA TIZI-OUZOU', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO Agence (num_agence, nom, adresse, direction_regionale_id, created_at, updated_at)
VALUES ('160', 'AGENCE NOUVELLE VILLE', 'Cité 145 Logements, EPLF Nouvelle Ville Tizi Ouzou', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO Agence (num_agence, nom, adresse, direction_regionale_id, created_at, updated_at)
VALUES ('161', 'AGENCE AIN EL HAMMAM', 'CITE 18 LOGEMENTS 15012 FREHA TIZI-OUZOU', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO Agence (num_agence, nom, adresse, direction_regionale_id, created_at, updated_at)
VALUES ('163', 'AGENCE DRAA BEN-KHEDDA', 'Cité 22 Logements Fathi, Draa Ben khedda, Tizi Ouzou', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO Agence (num_agence, nom, adresse, direction_regionale_id, created_at, updated_at)
VALUES ('171', 'AGENCE SI ABDELLAH', 'Rue Capitaine Si Abdellah, Tizi Ouzou', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO Agence (num_agence, nom, adresse, direction_regionale_id, created_at, updated_at)
VALUES ('183', 'AGENCE DRAA EL MIZAN', '16 Logemlent LSP, Draa El Mizan, Tizi Ouzou', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);




-- Assuming agency 1 corresponds to communes 1, 2, 3, and agency 2 corresponds to communes 4, 5, 6
INSERT INTO Agence_Commune (agence_id, commune_id) VALUES (1, 14);
INSERT INTO Agence_Commune (agence_id, commune_id) VALUES (2, 12);
INSERT INTO Agence_Commune (agence_id, commune_id) VALUES (3, 4);
INSERT INTO Agence_Commune (agence_id, commune_id) VALUES (4, 1);
INSERT INTO Agence_Commune (agence_id, commune_id) VALUES (5, 5);
INSERT INTO Agence_Commune (agence_id, commune_id) VALUES (6, 1);
INSERT INTO Agence_Commune (agence_id, commune_id) VALUES (10, 2);
INSERT INTO Agence_Commune (agence_id, commune_id) VALUES (10, 3);
INSERT INTO Agence_Commune (agence_id, commune_id) VALUES (3, 15);
INSERT INTO Agence_Commune (agence_id, commune_id) VALUES (5, 13);
INSERT INTO Agence_Commune (agence_id, commune_id) VALUES (8, 9);
INSERT INTO Agence_Commune (agence_id, commune_id) VALUES (8, 10);
INSERT INTO Agence_Commune (agence_id, commune_id) VALUES (7, 6);
INSERT INTO Agence_Commune (agence_id, commune_id) VALUES (9, 1);


