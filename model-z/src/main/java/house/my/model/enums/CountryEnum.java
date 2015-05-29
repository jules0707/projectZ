package house.my.model.enums;

import java.util.HashMap;
import java.util.Map;

// norme ISO 3166
public enum CountryEnum {

  AD, // Andorre
  AE, // Emirats Arabes Unis
  AF, // Afghanistan
  AI, // Anguilla
  AL, // Albanie
  AM, // Arménie
  AO, // Angola
  AQ, // Antarctique
  AR, // Argentine
  AT, // Autriche
  AU, // Australie
  AZ, // Azerbaidjan
  BA, // Bosnie Herzégovine
  BB, // Barbade
  BD, // Bangladesh
  BE, // Belgique
  BF, // Burkina Faso
  BG, // Bulgarie
  BH, // Bahrain
  BI, // Burundi
  BJ, // Bénin
  BL, // Saint-Barthélemy
  BM, // Bermude
  BN, // Brunei
  BO, // Bolivie
  BR, // Brésil
  BS, // Bahamas
  BT, // Bhoutan
  BW, // Botswana
  BY, // Biélorussie
  BZ, // Belize
  CA, // Canada
  CD, // Congo, République Démocratique du
  CF, // République centrafricaine
  CG, // Congo
  CH, // Suisse
  CI, // C\u00f4te d'Ivoire
  CL, // Chili
  CM, // Cameroun
  CN, // Chine
  CO, // Colombie
  CR, // Costa Rica
  CU, // Cuba
  CV, // Cap Vert
  CY, // Chypre
  CZ, // République Tchèque
  DE, // Allemagne
  DJ, // Djibouti
  DK, // Danemark
  DO, // République Dominicaine
  DZ, // Algérie
  DM, // Dominique
  EC, // Equateur
  EE, // Estonie
  EG, // Egypte
  EH, // Sahara Occidental
  ER, // Erythrée
  ES, // Espagne
  ET, // Ethiopie
  FI, // Finlande
  FJ, // Fiji
  FO, // Iles Féroé
  FR, // France
  GA, // Gabon
  GB, // Grande-Bretagne
  GD, // Grenade
  GE, // Géorgie
  GF, // Guyane française
  GH, // Ghana
  GI, // Gibraltar
  GL, // Gröenland
  GM, // Gambie
  GN, // Guinée
  GP, // Guadeloupe
  GQ, // Guinée équatoriale
  GR, // Grèce
  GT, // Guatemala
  GW, // Guinée-Bissau
  GY, // Guyana
  HK, // Hong Kong
  HN, // Honduras
  HR, // Croatie
  HT, // Haïti
  HU, // Hongrie
  ID, // Indonésie
  IE, // Irlande
  IL, // Israël
  IN, // Inde
  IQ, // Irak
  IR, // Iran
  IS, // Islande
  IT, // Italie
  JM, // Jamaïque
  JO, // Jordanie
  JP, // Japon
  KE, // Kenya
  KG, // Kirghizistan
  KH, // Cambodge
  KM, // Comores
  KP, // Corée du nord
  KR, // Corée du sud
  KW, // Koweït
  KZ, // Kazakhstan
  LA, // Laos
  LB, // Liban
  LC, // Sainte Lucie
  LI, // Liechtenstein
  LK, // Sri Lanka
  LR, // Liberia
  LS, // Lesotho
  LT, // Lituanie
  LU, // Luxembourg
  LV, // Lettonie
  LY, // Libye
  MA, // Maroc
  MC, // Monaco
  MD, // Moldavie
  ME, // Monténégro
  MF, // Saint-Martin
  MG, // Madagascar
  MK, // Macédoine
  ML, // Mali
  MM, // Birmanie
  MN, // Mongolie
  MO, // Macao
  MQ, // Martinique
  MR, // Mauritanie
  MT, // Malte
  MU, // Ile Maurice
  MV, // Maldives
  MW, // Malawi
  MX, // Mexique
  MY, // Malaisie
  MZ, // Mozambique
  NA, // Namibie
  NC, // Nouvelle Calédonie
  NE, // Niger
  NG, // Nigéria
  NI, // Nicaragua
  NL, // Country-Bas
  NO, // Norvège
  NP, // Népal
  NU, // Niue
  NZ, // Nouvelle-Zélande
  OM, // Oman
  PA, // Panama
  PE, // Pérou
  PF, // Polynésie française
  PG, // Papouasie-Nouvelle Guinée
  PH, // Philippines
  PK, // Pakistan
  PL, // Pologne
  PM, // Saint Pierre et Miquelon
  PR, // Puerto Rico
  PS, // Palestine
  PT, // Portugal
  PY, // Paraguay
  QA, // Qatar
  RE, // Réunion
  RO, // Roumanie
  RS, // Serbie
  RU, // Russie, fédération de
  RW, // Rwanda
  SA, // Arabie Saoudite
  SB, // Salomon (Iles)
  SC, // Seychelles
  SD, // Soudan
  SE, // Suède
  SG, // Singapour
  SI, // Slovénie
  SK, // Slovaquie
  SL, // Sierra Leone
  SM, // Saint-Marin
  SN, // Sénégal
  SO, // Somalie
  SR, // Surinam
  ST, // Sao Tomé et Principe
  SV, // Salvador
  SY, // Syrie
  SZ, // Swaziland
  TD, // Tchad
  TF, // France (Terres australes)
  TG, // Togo
  TH, // Thaïlande
  TI, // Tibet
  TJ, // Tadjikistan
  TL, // Timor Oriental
  TM, // Turkmenistan
  TN, // Tunisie
  TR, // Turquie
  TT, // Trinité-et-Tobago
  TW, // Taïwan
  TZ, // Tanzanie
  UA, // Ukraine
  UG, // Ouganda
  UK, // Royaume-Uni
  US, // Etats-Unis
  UY, // Uruguay
  UZ, // Ouzbekistan
  VA, // Vatican
  VC, // Saint Vincent and the Grenadines
  VE, // Venezuela
  VN, // Vietnam
  VU, // Vanuatu
  WF, // Wallis and Futuna
  YE, // Yémen
  YT, // Mayotte
  ZA, // Afrique du Sud
  ZM, // Zambie
  ZW // Zimbabwe
  ;

  private CountryEnum() {
  }

  public static CountryEnum fromCodePays(String codePays) {
    for (CountryEnum country : CountryEnum.values()) {
      if (country.name().equals(codePays)) {
        return country;
      }
    }
    return null;
  }

  public static Map<String, String> getAsHashMap() {
    Map<String, String> list = new HashMap<String, String>();
    for (CountryEnum countryIso : CountryEnum.values()) {
      list.put(countryIso.name(), countryIso.name());
    }
    return list;
  }
}
