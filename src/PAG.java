import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PAG {
    public static double obliczWartoscFunkcji(int a, int b, int c, int x)
    {
        return (a) * Math.sin(x) + Math.sqrt(4 * (b)) + (c);
    }
    public static void runProgram()
    {
       List<Integer> listaX = new ArrayList<>();
       List<Double> listaWartosciFunkcjiDlaX = new ArrayList<>();

       List <Integer> wartosciArgumentowPotomkowPoMutacji = new ArrayList<>();
       List<Integer> wartosciFunkcjiDlaPotomkowPoMutacji = new ArrayList<>();

       List<Integer> listaMaxWartosciArgumentowPotomkowPoMutacji = new ArrayList<>();
       List<Integer> listaMaxWartosciFunkcjiDlaPotomkowPoMutacji = new ArrayList<>();

        System.out.println("Podaj współczynnik a, ENTER, b oraz c.");
        Scanner wspolczynniki = new Scanner(System.in);
        int a = wspolczynniki.nextInt();
        int b = wspolczynniki.nextInt();
        int c = wspolczynniki.nextInt();

        System.out.println();
        System.out.println("Podaj prawdopodobieństwo krzyżowania : ");
        Scanner scanner = new Scanner(System.in);
        double prawdopodobienstwoKrzyzowania = scanner.nextDouble();

        System.out.println();
        System.out.println("Podaj prawdopodobieństwo mutacji : ");
        Scanner scanner2 = new Scanner(System.in);
        double prawdopodobienstwoMutacji = scanner2.nextDouble();



        for(int n = 0; n < 1001; n++)
        {

                int x = 0;
                double y;
                double bmun = 0;


                 if(n==0) {
                    for (int i = 0; i < 20; i++) {
                        x = (int) Math.floor(Math.random() * 255);
                        listaX.add(x);
                        y = Math.round((obliczWartoscFunkcji(a, b, c, x)) * 100.0) / 100.0;
                        listaWartosciFunkcjiDlaX.add(y);
                    }

                 }

                 else if (n>=1)
                 {
                     listaX.clear();
                     listaWartosciFunkcjiDlaX.clear();

                     for(int z = 0; z<20; z++)
                     {
                         int argument = wartosciArgumentowPotomkowPoMutacji.get(z);
                         listaX.add(argument);
                         double wartosci = wartosciFunkcjiDlaPotomkowPoMutacji.get(z);
                         listaWartosciFunkcjiDlaX.add(wartosci);
                     }
                 }
                Collections.sort(listaWartosciFunkcjiDlaX);
                System.out.println("Lista wyglada tak " + listaWartosciFunkcjiDlaX);


                double mun = listaWartosciFunkcjiDlaX.get(0);

                List <Double> tymczasowaLista  = new ArrayList <>();
                tymczasowaLista.clear();

                for(int i=0; i<listaWartosciFunkcjiDlaX.size(); i++)
                {
                    //listaWartosciFunkcjiDlaX.get(i) = listaWartosciFunkcjiDlaX.get(i) - mun + 1;
                    bmun = listaWartosciFunkcjiDlaX.get(i) - mun + 1;

                    tymczasowaLista.add(bmun);
                }
                listaWartosciFunkcjiDlaX.clear();

                //double numerek;

                for(int i=0;i<tymczasowaLista.size();i++)
                {
                    //numerek = tymczasowaLista.get(i);
                    //listaWartosciFunkcjiDlaX.add(numerek);
                    listaWartosciFunkcjiDlaX.add(tymczasowaLista.get(i));
                }


                System.out.println("Wylosowane wartości x : " + listaX);
                System.out.println();
                System.out.println("Wartości funkcji dla x : " + listaWartosciFunkcjiDlaX);
                System.out.println();

                double sumaWartosciFunkcjiDlaX = 0;

                for (int i = 0; i < listaWartosciFunkcjiDlaX.size(); i++) {
                    sumaWartosciFunkcjiDlaX = sumaWartosciFunkcjiDlaX + listaWartosciFunkcjiDlaX.get(i) * 100.0 / 100.0;
                }
                System.out.println("Suma wartości funkcji dla wszystkich x-ów wynosi : " + sumaWartosciFunkcjiDlaX);
                System.out.println();

                List<Double> procentWMetodzieRuletki = new ArrayList<>();

                for (int i = 0; i < listaWartosciFunkcjiDlaX.size(); i++) {
                    double procentWieleMiejsc = listaWartosciFunkcjiDlaX.get(i) * 100 / sumaWartosciFunkcjiDlaX;
                    double procent = Math.round(procentWieleMiejsc * 100.0) / 100.0;
                    procentWMetodzieRuletki.add(procent);
                    System.out.println("Udział chromosomu nr " + (i + 1) + ", by wybrać rodziców wynosi " + procent + " %.");
                }

                List<Double> dodawanieProcentow = new ArrayList<>();

                for (int i = 0; i < procentWMetodzieRuletki.size(); i++) {
                    if (i == 0) {
                        dodawanieProcentow.add(procentWMetodzieRuletki.get(0));
                    } else {
                        dodawanieProcentow.add(dodawanieProcentow.get(i - 1) + procentWMetodzieRuletki.get(i));
                    }
                }

                System.out.println();
                System.out.println("Sumy procentów : " + dodawanieProcentow);

                List<Integer> wylosowaniRodzice = new ArrayList<>();
                int rodzic;

                for (int i = 0; i < 20; i++) {
                    rodzic = (int) (Math.random() * 99);
                    wylosowaniRodzice.add(rodzic + 1);
                }

                System.out.println();
                System.out.println("Wylosowano następujących rodziców " + wylosowaniRodzice);

                List<Integer> numeryChromosomowWylosowanychRodzicow = new ArrayList<>();
                List<Integer> numeryChromosomowWylosowanychRodzicowWedlugListyX = new ArrayList<>();

                for (int i = 0; i < 20; i++) {
                    for (int j = 0; j < 20; j++) {
                        if (wylosowaniRodzice.get(i) < dodawanieProcentow.get(j)) {
                            int numerChromosomu = j + 1;
                            int numerChromosomuZListyX = listaX.get(numerChromosomu - 1);

                            numeryChromosomowWylosowanychRodzicow.add(numerChromosomu);
                            numeryChromosomowWylosowanychRodzicowWedlugListyX.add(numerChromosomuZListyX);
                            break;
                        }
                    }
                }
                System.out.println();
                System.out.println("Chromosomy wylosowanych rodziców na podstawie metody ruletki to : " + numeryChromosomowWylosowanychRodzicow);
                System.out.println();
                System.out.println("Chromosomy wylosowanych rodziców według populacji początkowej to : " + numeryChromosomowWylosowanychRodzicowWedlugListyX);
                System.out.println();

                List<String> numeryChromWylosRodzJakoString = new ArrayList<>();

                for (int i = 0; i < numeryChromosomowWylosowanychRodzicowWedlugListyX.size(); i++) {
                    int tymczasowyNumerChromosomu = numeryChromosomowWylosowanychRodzicowWedlugListyX.get(i);
                    String binarnyZapis = Integer.toBinaryString(tymczasowyNumerChromosomu);

                    if (binarnyZapis.length() < 8) {
                        int roznicaDlugosciZapisu = 8 - binarnyZapis.length();
                        String brakujaceZero = "0";

                        for (int j = 0; j < roznicaDlugosciZapisu; j++) {
                            binarnyZapis = brakujaceZero.concat(binarnyZapis);
                        }
                    }
                    numeryChromWylosRodzJakoString.add(binarnyZapis);
                }

                System.out.println("Zapis binarny chromosomów rodziców : " + numeryChromWylosRodzJakoString);


                double wylosowaneLiczbyKrzyzowanie;

                List<Double> listaWylosowanychLiczbKrzyzowanie = new ArrayList<>();

                for (int i = 0; i < 10; i++) {
                    wylosowaneLiczbyKrzyzowanie = Math.random();
                    listaWylosowanychLiczbKrzyzowanie.add(wylosowaneLiczbyKrzyzowanie);
                }


                System.out.println();
                System.out.println("Wylosowane liczby : " + listaWylosowanychLiczbKrzyzowanie);
                System.out.println();


                List<Integer> punktyKrzyzowania = new ArrayList<>();
                int punktKrzyzowania;

                for (int i = 0; i < listaWylosowanychLiczbKrzyzowanie.size(); i++) {

                    punktKrzyzowania = (int) (Math.random() * 4 + 1);
                    punktyKrzyzowania.add(punktKrzyzowania);

                    if (listaWylosowanychLiczbKrzyzowanie.get(i) > prawdopodobienstwoKrzyzowania) {
                        punktyKrzyzowania.set(i, 0);
                    }
                }

                System.out.println("Punkty krzyżowania : " + punktyKrzyzowania);
                System.out.println();


                //Krzyzowanie

                String str1, str2;
                String potomekPierwszy, potomekDrugi;

                List<String> potomkowie = new ArrayList<>();


                for (int i = 0; i < punktyKrzyzowania.size(); i++) {
                    if (i == 0) {

                        str1 = numeryChromWylosRodzJakoString.get(0);
                        str2 = numeryChromWylosRodzJakoString.get(1);

                        if (punktyKrzyzowania.get(0) == 0) {
                            potomkowie.add(str1);
                            potomkowie.add(str2);

                        } else {

                            potomekPierwszy = str1.substring(0, punktyKrzyzowania.get(i)) + str2.substring(punktyKrzyzowania.get(i));
                            potomekDrugi = str2.substring(0, punktyKrzyzowania.get(i)) + str1.substring(punktyKrzyzowania.get(i));

                            potomkowie.add(potomekPierwszy);
                            potomkowie.add(potomekDrugi);
                        }

                    } else if (i == 1) {
                        str1 = numeryChromWylosRodzJakoString.get(2);
                        str2 = numeryChromWylosRodzJakoString.get(3);

                        if (punktyKrzyzowania.get(0) == 0) {
                            potomkowie.add(str1);
                            potomkowie.add(str2);

                        } else {

                            potomekPierwszy = str1.substring(0, punktyKrzyzowania.get(i)) + str2.substring(punktyKrzyzowania.get(i));
                            potomekDrugi = str2.substring(0, punktyKrzyzowania.get(i)) + str1.substring(punktyKrzyzowania.get(i));

                            potomkowie.add(potomekPierwszy);
                            potomkowie.add(potomekDrugi);
                        }

                    } else if (i == 2) {
                        str1 = numeryChromWylosRodzJakoString.get(4);
                        str2 = numeryChromWylosRodzJakoString.get(5);

                        if (punktyKrzyzowania.get(0) == 0) {
                            potomkowie.add(str1);
                            potomkowie.add(str2);

                        } else {

                            potomekPierwszy = str1.substring(0, punktyKrzyzowania.get(i)) + str2.substring(punktyKrzyzowania.get(i));
                            potomekDrugi = str2.substring(0, punktyKrzyzowania.get(i)) + str1.substring(punktyKrzyzowania.get(i));

                            potomkowie.add(potomekPierwszy);
                            potomkowie.add(potomekDrugi);
                        }
                    } else if (i == 3) {
                        str1 = numeryChromWylosRodzJakoString.get(6);
                        str2 = numeryChromWylosRodzJakoString.get(7);

                        if (punktyKrzyzowania.get(0) == 0) {
                            potomkowie.add(str1);
                            potomkowie.add(str2);

                        } else {

                            potomekPierwszy = str1.substring(0, punktyKrzyzowania.get(i)) + str2.substring(punktyKrzyzowania.get(i));
                            potomekDrugi = str2.substring(0, punktyKrzyzowania.get(i)) + str1.substring(punktyKrzyzowania.get(i));

                            potomkowie.add(potomekPierwszy);
                            potomkowie.add(potomekDrugi);
                        }

                    } else if (i == 4) {
                        str1 = numeryChromWylosRodzJakoString.get(8);
                        str2 = numeryChromWylosRodzJakoString.get(9);

                        if (punktyKrzyzowania.get(0) == 0) {
                            potomkowie.add(str1);
                            potomkowie.add(str2);

                        } else {

                            potomekPierwszy = str1.substring(0, punktyKrzyzowania.get(i)) + str2.substring(punktyKrzyzowania.get(i));
                            potomekDrugi = str2.substring(0, punktyKrzyzowania.get(i)) + str1.substring(punktyKrzyzowania.get(i));

                            potomkowie.add(potomekPierwszy);
                            potomkowie.add(potomekDrugi);
                        }

                    } else if (i == 5) {
                        str1 = numeryChromWylosRodzJakoString.get(10);
                        str2 = numeryChromWylosRodzJakoString.get(11);

                        if (punktyKrzyzowania.get(0) == 0) {
                            potomkowie.add(str1);
                            potomkowie.add(str2);

                        } else {

                            potomekPierwszy = str1.substring(0, punktyKrzyzowania.get(i)) + str2.substring(punktyKrzyzowania.get(i));
                            potomekDrugi = str2.substring(0, punktyKrzyzowania.get(i)) + str1.substring(punktyKrzyzowania.get(i));

                            potomkowie.add(potomekPierwszy);
                            potomkowie.add(potomekDrugi);
                        }

                    } else if (i == 6) {
                        str1 = numeryChromWylosRodzJakoString.get(12);
                        str2 = numeryChromWylosRodzJakoString.get(13);

                        if (punktyKrzyzowania.get(0) == 0) {
                            potomkowie.add(str1);
                            potomkowie.add(str2);

                        } else {

                            potomekPierwszy = str1.substring(0, punktyKrzyzowania.get(i)) + str2.substring(punktyKrzyzowania.get(i));
                            potomekDrugi = str2.substring(0, punktyKrzyzowania.get(i)) + str1.substring(punktyKrzyzowania.get(i));

                            potomkowie.add(potomekPierwszy);
                            potomkowie.add(potomekDrugi);
                        }

                    } else if (i == 7) {
                        str1 = numeryChromWylosRodzJakoString.get(14);
                        str2 = numeryChromWylosRodzJakoString.get(15);

                        if (punktyKrzyzowania.get(0) == 0) {
                            potomkowie.add(str1);
                            potomkowie.add(str2);

                        } else {

                            potomekPierwszy = str1.substring(0, punktyKrzyzowania.get(i)) + str2.substring(punktyKrzyzowania.get(i));
                            potomekDrugi = str2.substring(0, punktyKrzyzowania.get(i)) + str1.substring(punktyKrzyzowania.get(i));

                            potomkowie.add(potomekPierwszy);
                            potomkowie.add(potomekDrugi);
                        }

                    } else if (i == 8) {
                        str1 = numeryChromWylosRodzJakoString.get(16);
                        str2 = numeryChromWylosRodzJakoString.get(17);

                        if (punktyKrzyzowania.get(0) == 0) {
                            potomkowie.add(str1);
                            potomkowie.add(str2);

                        } else {

                            potomekPierwszy = str1.substring(0, punktyKrzyzowania.get(i)) + str2.substring(punktyKrzyzowania.get(i));
                            potomekDrugi = str2.substring(0, punktyKrzyzowania.get(i)) + str1.substring(punktyKrzyzowania.get(i));

                            potomkowie.add(potomekPierwszy);
                            potomkowie.add(potomekDrugi);
                        }

                    } else if (i == 9) {
                        str1 = numeryChromWylosRodzJakoString.get(18);
                        str2 = numeryChromWylosRodzJakoString.get(19);

                        if (punktyKrzyzowania.get(0) == 0) {
                            potomkowie.add(str1);
                            potomkowie.add(str2);

                        } else {

                            potomekPierwszy = str1.substring(0, punktyKrzyzowania.get(i)) + str2.substring(punktyKrzyzowania.get(i));
                            potomekDrugi = str2.substring(0, punktyKrzyzowania.get(i)) + str1.substring(punktyKrzyzowania.get(i));

                            potomkowie.add(potomekPierwszy);
                            potomkowie.add(potomekDrugi);
                        }

                    }
                }


                System.out.println("Potomkowie po krzyżowaniu : " + potomkowie);
                System.out.println();

                //Liczenie wartosci funkcji dla potomkow po krzyzowaniu

                String tymczasowyPotomek;
                int tymczasowyPotomekPoZamianie;
                int wartoscFunkcjiPotomkaPoKrzyzowaniu;
                List<Integer> wartosciFunkcjiDlaPotomkowPoKrzyzowaniu = new ArrayList<>();
                List<Integer> wartosciArgumentowPotomkowPoKrzyzowaniu = new ArrayList<>();

                for (int i = 0; i < potomkowie.size(); i++) {

                    tymczasowyPotomek = potomkowie.get(i);
                    tymczasowyPotomekPoZamianie = Integer.parseInt(tymczasowyPotomek, 2);
                    wartoscFunkcjiPotomkaPoKrzyzowaniu = (int) Math.round(obliczWartoscFunkcji(a, b, c, tymczasowyPotomekPoZamianie));
                    wartosciFunkcjiDlaPotomkowPoKrzyzowaniu.add(wartoscFunkcjiPotomkaPoKrzyzowaniu);
                    wartosciArgumentowPotomkowPoKrzyzowaniu.add(tymczasowyPotomekPoZamianie);
                }

                System.out.println("Wartości funkcji dla potomków po krzyżowaniu : " + wartosciFunkcjiDlaPotomkowPoKrzyzowaniu);
                System.out.println();
                System.out.println("Wartości argumentów dla potomków po krzyżowaniu : " + wartosciArgumentowPotomkowPoKrzyzowaniu);
                System.out.println();



                double wylosowaneLiczbyMutacja;

                List<Double> listaWylosowanychLiczbMutacja = new ArrayList<>();

                for (int i = 0; i < 20; i++) {
                    wylosowaneLiczbyMutacja = Math.random();
                    listaWylosowanychLiczbMutacja.add(wylosowaneLiczbyMutacja);
                }

                System.out.println();
                System.out.println("Wylosowane liczby : " + listaWylosowanychLiczbMutacja);
                System.out.println();


                List<Integer> punktyMutacji = new ArrayList<>();
                int punktMutacji;

                for (int i = 0; i < listaWylosowanychLiczbMutacja.size(); i++) {

                    punktMutacji = (int) (Math.random() * 4 + 1);
                    punktyMutacji.add(punktMutacji);

                    if (listaWylosowanychLiczbMutacja.get(i) > prawdopodobienstwoMutacji) {
                        punktyMutacji.set(i, 0);
                    }
                }

                System.out.println("Punkty mutacji : " + punktyMutacji);
                System.out.println();

                //Mutacja

                String potomekPoKrzy;
                String potomekJedenPoMutacji;
                List<String> potomkowiePoMutacji = new ArrayList<>();


                for (int i = 0; i < punktyMutacji.size(); i++)
                {
                    potomekPoKrzy = potomkowie.get(i);
                    punktMutacji = punktyMutacji.get(i);

                    if (i == 0) {

                        if (punktyMutacji.get(i) == 0) {
                            potomkowiePoMutacji.add(potomekPoKrzy);
                        } else {
                            if (potomkowie.get(i).charAt(punktMutacji - 1) == '0') {
                                potomekJedenPoMutacji = potomekPoKrzy.substring(0, punktMutacji - 1) + '1' + potomekPoKrzy.substring((punktMutacji));
                                potomkowiePoMutacji.add(potomekJedenPoMutacji);
                            } else if (potomkowie.get(i).charAt(punktMutacji - 1) == '1') {
                                potomekJedenPoMutacji = potomekPoKrzy.substring(0, punktMutacji - 1) + '0' + potomekPoKrzy.substring(punktMutacji);
                                potomkowiePoMutacji.add(potomekJedenPoMutacji);
                            }
                        }
                    } else if (i == 1) {

                        if (punktyMutacji.get(i) == 0) {
                            potomkowiePoMutacji.add(potomekPoKrzy);
                        } else if (punktyMutacji.get(i) > 0) {
                            if (potomkowie.get(i).charAt(punktMutacji - 1) == '0') {
                                potomekJedenPoMutacji = potomekPoKrzy.substring(0, punktMutacji - 1) + '1' + potomekPoKrzy.substring((punktMutacji));
                                potomkowiePoMutacji.add(potomekJedenPoMutacji);
                            } else if (potomkowie.get(i).charAt(punktMutacji - 1) == '1') {
                                potomekJedenPoMutacji = potomekPoKrzy.substring(0, punktMutacji - 1) + '0' + potomekPoKrzy.substring(punktMutacji);
                                potomkowiePoMutacji.add(potomekJedenPoMutacji);
                            }
                        }
                    } else if (i == 2) {

                        if (punktyMutacji.get(i) == 0) {
                            potomkowiePoMutacji.add(potomekPoKrzy);
                        } else if (punktyMutacji.get(i) > 0) {
                            if (potomkowie.get(i).charAt(punktMutacji - 1) == '0') {
                                potomekJedenPoMutacji = potomekPoKrzy.substring(0, punktMutacji - 1) + '1' + potomekPoKrzy.substring((punktMutacji));
                                potomkowiePoMutacji.add(potomekJedenPoMutacji);
                            } else if (potomkowie.get(i).charAt(punktMutacji - 1) == '1') {
                                potomekJedenPoMutacji = potomekPoKrzy.substring(0, punktMutacji - 1) + '0' + potomekPoKrzy.substring(punktMutacji);
                                potomkowiePoMutacji.add(potomekJedenPoMutacji);
                            }
                        }

                    } else if (i == 3) {

                        if (punktyMutacji.get(i) == 0) {
                            potomkowiePoMutacji.add(potomekPoKrzy);
                        } else if (punktyMutacji.get(i) > 0) {
                            if (potomkowie.get(i).charAt(punktMutacji - 1) == '0') {
                                potomekJedenPoMutacji = potomekPoKrzy.substring(0, punktMutacji - 1) + '1' + potomekPoKrzy.substring((punktMutacji));
                                potomkowiePoMutacji.add(potomekJedenPoMutacji);
                            } else if (potomkowie.get(i).charAt(punktMutacji - 1) == '1') {
                                potomekJedenPoMutacji = potomekPoKrzy.substring(0, punktMutacji - 1) + '0' + potomekPoKrzy.substring(punktMutacji);
                                potomkowiePoMutacji.add(potomekJedenPoMutacji);
                            }
                        }
                    } else if (i == 4) {

                        if (punktyMutacji.get(i) == 0) {
                            potomkowiePoMutacji.add(potomekPoKrzy);
                        } else if (punktyMutacji.get(i) > 0) {
                            if (potomkowie.get(i).charAt(punktMutacji - 1) == '0') {
                                potomekJedenPoMutacji = potomekPoKrzy.substring(0, punktMutacji - 1) + '1' + potomekPoKrzy.substring((punktMutacji));
                                potomkowiePoMutacji.add(potomekJedenPoMutacji);
                            } else if (potomkowie.get(i).charAt(punktMutacji - 1) == '1') {
                                potomekJedenPoMutacji = potomekPoKrzy.substring(0, punktMutacji - 1) + '0' + potomekPoKrzy.substring(punktMutacji);
                                potomkowiePoMutacji.add(potomekJedenPoMutacji);
                            }
                        }
                    } else if (i == 5) {

                        if (punktyMutacji.get(i) == 0) {
                            potomkowiePoMutacji.add(potomekPoKrzy);
                        } else if (punktyMutacji.get(i) > 0) {
                            if (potomkowie.get(i).charAt(punktMutacji - 1) == '0') {
                                potomekJedenPoMutacji = potomekPoKrzy.substring(0, punktMutacji - 1) + '1' + potomekPoKrzy.substring((punktMutacji));
                                potomkowiePoMutacji.add(potomekJedenPoMutacji);
                            } else if (potomkowie.get(i).charAt(punktMutacji - 1) == '1') {
                                potomekJedenPoMutacji = potomekPoKrzy.substring(0, punktMutacji - 1) + '0' + potomekPoKrzy.substring(punktMutacji);
                                potomkowiePoMutacji.add(potomekJedenPoMutacji);
                            }
                        }
                    } else if (i == 6) {

                        if (punktyMutacji.get(i) == 0) {
                            potomkowiePoMutacji.add(potomekPoKrzy);
                        } else if (punktyMutacji.get(i) > 0) {
                            if (potomkowie.get(i).charAt(punktMutacji - 1) == '0') {
                                potomekJedenPoMutacji = potomekPoKrzy.substring(0, punktMutacji - 1) + '1' + potomekPoKrzy.substring((punktMutacji));
                                potomkowiePoMutacji.add(potomekJedenPoMutacji);
                            } else if (potomkowie.get(i).charAt(punktMutacji - 1) == '1') {
                                potomekJedenPoMutacji = potomekPoKrzy.substring(0, punktMutacji - 1) + '0' + potomekPoKrzy.substring(punktMutacji);
                                potomkowiePoMutacji.add(potomekJedenPoMutacji);
                            }
                        }
                    } else if (i == 7) {

                        if (punktyMutacji.get(i) == 0) {
                            potomkowiePoMutacji.add(potomekPoKrzy);
                        } else if (punktyMutacji.get(i) > 0) {
                            if (potomkowie.get(i).charAt(punktMutacji - 1) == '0') {
                                potomekJedenPoMutacji = potomekPoKrzy.substring(0, punktMutacji - 1) + '1' + potomekPoKrzy.substring((punktMutacji));
                                potomkowiePoMutacji.add(potomekJedenPoMutacji);
                            } else if (potomkowie.get(i).charAt(punktMutacji - 1) == '1') {
                                potomekJedenPoMutacji = potomekPoKrzy.substring(0, punktMutacji - 1) + '0' + potomekPoKrzy.substring(punktMutacji);
                                potomkowiePoMutacji.add(potomekJedenPoMutacji);
                            }
                        }
                    } else if (i == 8) {

                        if (punktyMutacji.get(i) == 0) {
                            potomkowiePoMutacji.add(potomekPoKrzy);
                        } else if (punktyMutacji.get(i) > 0) {
                            if (potomkowie.get(i).charAt(punktMutacji - 1) == '0') {
                                potomekJedenPoMutacji = potomekPoKrzy.substring(0, punktMutacji - 1) + '1' + potomekPoKrzy.substring((punktMutacji));
                                potomkowiePoMutacji.add(potomekJedenPoMutacji);
                            } else if (potomkowie.get(i).charAt(punktMutacji - 1) == '1') {
                                potomekJedenPoMutacji = potomekPoKrzy.substring(0, punktMutacji - 1) + '0' + potomekPoKrzy.substring(punktMutacji);
                                potomkowiePoMutacji.add(potomekJedenPoMutacji);
                            }
                        }
                    } else if (i == 9) {

                        if (punktyMutacji.get(i) == 0) {
                            potomkowiePoMutacji.add(potomekPoKrzy);
                        } else if (punktyMutacji.get(i) > 0) {
                            if (potomkowie.get(i).charAt(punktMutacji - 1) == '0') {
                                potomekJedenPoMutacji = potomekPoKrzy.substring(0, punktMutacji - 1) + '1' + potomekPoKrzy.substring((punktMutacji));
                                potomkowiePoMutacji.add(potomekJedenPoMutacji);
                            } else if (potomkowie.get(i).charAt(punktMutacji - 1) == '1') {
                                potomekJedenPoMutacji = potomekPoKrzy.substring(0, punktMutacji - 1) + '0' + potomekPoKrzy.substring(punktMutacji);
                                potomkowiePoMutacji.add(potomekJedenPoMutacji);
                            }
                        }
                    }
                    else if (i == 10) {

                        if (punktyMutacji.get(i) == 0) {
                            potomkowiePoMutacji.add(potomekPoKrzy);
                        } else if (punktyMutacji.get(i) > 0) {
                            if (potomkowie.get(i).charAt(punktMutacji - 1) == '0') {
                                potomekJedenPoMutacji = potomekPoKrzy.substring(0, punktMutacji - 1) + '1' + potomekPoKrzy.substring((punktMutacji));
                                potomkowiePoMutacji.add(potomekJedenPoMutacji);
                            } else if (potomkowie.get(i).charAt(punktMutacji - 1) == '1') {
                                potomekJedenPoMutacji = potomekPoKrzy.substring(0, punktMutacji - 1) + '0' + potomekPoKrzy.substring(punktMutacji);
                                potomkowiePoMutacji.add(potomekJedenPoMutacji);
                            }
                        }
                    }
                    else if (i == 11) {

                        if (punktyMutacji.get(i) == 0) {
                            potomkowiePoMutacji.add(potomekPoKrzy);
                        } else if (punktyMutacji.get(i) > 0) {
                            if (potomkowie.get(i).charAt(punktMutacji - 1) == '0') {
                                potomekJedenPoMutacji = potomekPoKrzy.substring(0, punktMutacji - 1) + '1' + potomekPoKrzy.substring((punktMutacji));
                                potomkowiePoMutacji.add(potomekJedenPoMutacji);
                            } else if (potomkowie.get(i).charAt(punktMutacji - 1) == '1') {
                                potomekJedenPoMutacji = potomekPoKrzy.substring(0, punktMutacji - 1) + '0' + potomekPoKrzy.substring(punktMutacji);
                                potomkowiePoMutacji.add(potomekJedenPoMutacji);
                            }
                        }
                    }
                    else if (i == 12) {

                        if (punktyMutacji.get(i) == 0) {
                            potomkowiePoMutacji.add(potomekPoKrzy);
                        } else if (punktyMutacji.get(i) > 0) {
                            if (potomkowie.get(i).charAt(punktMutacji - 1) == '0') {
                                potomekJedenPoMutacji = potomekPoKrzy.substring(0, punktMutacji - 1) + '1' + potomekPoKrzy.substring((punktMutacji));
                                potomkowiePoMutacji.add(potomekJedenPoMutacji);
                            } else if (potomkowie.get(i).charAt(punktMutacji - 1) == '1') {
                                potomekJedenPoMutacji = potomekPoKrzy.substring(0, punktMutacji - 1) + '0' + potomekPoKrzy.substring(punktMutacji);
                                potomkowiePoMutacji.add(potomekJedenPoMutacji);
                            }
                        }
                    }
                    else if (i == 13) {

                        if (punktyMutacji.get(i) == 0) {
                            potomkowiePoMutacji.add(potomekPoKrzy);
                        } else if (punktyMutacji.get(i) > 0) {
                            if (potomkowie.get(i).charAt(punktMutacji - 1) == '0') {
                                potomekJedenPoMutacji = potomekPoKrzy.substring(0, punktMutacji - 1) + '1' + potomekPoKrzy.substring((punktMutacji));
                                potomkowiePoMutacji.add(potomekJedenPoMutacji);
                            } else if (potomkowie.get(i).charAt(punktMutacji - 1) == '1') {
                                potomekJedenPoMutacji = potomekPoKrzy.substring(0, punktMutacji - 1) + '0' + potomekPoKrzy.substring(punktMutacji);
                                potomkowiePoMutacji.add(potomekJedenPoMutacji);
                            }
                        }
                    }
                    else if (i == 14) {

                        if (punktyMutacji.get(i) == 0) {
                            potomkowiePoMutacji.add(potomekPoKrzy);
                        } else if (punktyMutacji.get(i) > 0) {
                            if (potomkowie.get(i).charAt(punktMutacji - 1) == '0') {
                                potomekJedenPoMutacji = potomekPoKrzy.substring(0, punktMutacji - 1) + '1' + potomekPoKrzy.substring((punktMutacji));
                                potomkowiePoMutacji.add(potomekJedenPoMutacji);
                            } else if (potomkowie.get(i).charAt(punktMutacji - 1) == '1') {
                                potomekJedenPoMutacji = potomekPoKrzy.substring(0, punktMutacji - 1) + '0' + potomekPoKrzy.substring(punktMutacji);
                                potomkowiePoMutacji.add(potomekJedenPoMutacji);
                            }
                        }
                    }
                    else if (i == 15) {

                        if (punktyMutacji.get(i) == 0) {
                            potomkowiePoMutacji.add(potomekPoKrzy);
                        } else if (punktyMutacji.get(i) > 0) {
                            if (potomkowie.get(i).charAt(punktMutacji - 1) == '0') {
                                potomekJedenPoMutacji = potomekPoKrzy.substring(0, punktMutacji - 1) + '1' + potomekPoKrzy.substring((punktMutacji));
                                potomkowiePoMutacji.add(potomekJedenPoMutacji);
                            } else if (potomkowie.get(i).charAt(punktMutacji - 1) == '1') {
                                potomekJedenPoMutacji = potomekPoKrzy.substring(0, punktMutacji - 1) + '0' + potomekPoKrzy.substring(punktMutacji);
                                potomkowiePoMutacji.add(potomekJedenPoMutacji);
                            }
                        }
                    }
                    else if (i == 16) {

                        if (punktyMutacji.get(i) == 0) {
                            potomkowiePoMutacji.add(potomekPoKrzy);
                        } else if (punktyMutacji.get(i) > 0) {
                            if (potomkowie.get(i).charAt(punktMutacji - 1) == '0') {
                                potomekJedenPoMutacji = potomekPoKrzy.substring(0, punktMutacji - 1) + '1' + potomekPoKrzy.substring((punktMutacji));
                                potomkowiePoMutacji.add(potomekJedenPoMutacji);
                            } else if (potomkowie.get(i).charAt(punktMutacji - 1) == '1') {
                                potomekJedenPoMutacji = potomekPoKrzy.substring(0, punktMutacji - 1) + '0' + potomekPoKrzy.substring(punktMutacji);
                                potomkowiePoMutacji.add(potomekJedenPoMutacji);
                            }
                        }
                    }
                    else if (i == 17) {

                        if (punktyMutacji.get(i) == 0) {
                            potomkowiePoMutacji.add(potomekPoKrzy);
                        } else if (punktyMutacji.get(i) > 0) {
                            if (potomkowie.get(i).charAt(punktMutacji - 1) == '0') {
                                potomekJedenPoMutacji = potomekPoKrzy.substring(0, punktMutacji - 1) + '1' + potomekPoKrzy.substring((punktMutacji));
                                potomkowiePoMutacji.add(potomekJedenPoMutacji);
                            } else if (potomkowie.get(i).charAt(punktMutacji - 1) == '1') {
                                potomekJedenPoMutacji = potomekPoKrzy.substring(0, punktMutacji - 1) + '0' + potomekPoKrzy.substring(punktMutacji);
                                potomkowiePoMutacji.add(potomekJedenPoMutacji);
                            }
                        }
                    }
                    else if (i == 18) {

                        if (punktyMutacji.get(i) == 0) {
                            potomkowiePoMutacji.add(potomekPoKrzy);
                        } else if (punktyMutacji.get(i) > 0) {
                            if (potomkowie.get(i).charAt(punktMutacji - 1) == '0') {
                                potomekJedenPoMutacji = potomekPoKrzy.substring(0, punktMutacji - 1) + '1' + potomekPoKrzy.substring((punktMutacji));
                                potomkowiePoMutacji.add(potomekJedenPoMutacji);
                            } else if (potomkowie.get(i).charAt(punktMutacji - 1) == '1') {
                                potomekJedenPoMutacji = potomekPoKrzy.substring(0, punktMutacji - 1) + '0' + potomekPoKrzy.substring(punktMutacji);
                                potomkowiePoMutacji.add(potomekJedenPoMutacji);
                            }
                        }
                    }
                    else if (i == 19) {

                        if (punktyMutacji.get(i) == 0) {
                            potomkowiePoMutacji.add(potomekPoKrzy);
                        } else if (punktyMutacji.get(i) > 0) {
                            if (potomkowie.get(i).charAt(punktMutacji - 1) == '0') {
                                potomekJedenPoMutacji = potomekPoKrzy.substring(0, punktMutacji - 1) + '1' + potomekPoKrzy.substring((punktMutacji));
                                potomkowiePoMutacji.add(potomekJedenPoMutacji);
                            } else if (potomkowie.get(i).charAt(punktMutacji - 1) == '1') {
                                potomekJedenPoMutacji = potomekPoKrzy.substring(0, punktMutacji - 1) + '0' + potomekPoKrzy.substring(punktMutacji);
                                potomkowiePoMutacji.add(potomekJedenPoMutacji);
                            }
                        }
                    }
                }
                System.out.println("Potomkowie po mutacji : " + potomkowiePoMutacji);
                System.out.println();

                //Liczenie wartosci funkcji dla potomkow po mutacji
                wartosciFunkcjiDlaPotomkowPoMutacji.clear();
                wartosciArgumentowPotomkowPoMutacji.clear();

                String tymczasPotomek;
                int tymczasPotomekPoZamianie;
                int wartoscFunkcjiPotomkaPoMutacji;


                for (int i = 0; i < potomkowiePoMutacji.size(); i++) {

                    tymczasPotomek = potomkowiePoMutacji.get(i);
                    tymczasPotomekPoZamianie = Integer.parseInt(tymczasPotomek, 2);
                    wartoscFunkcjiPotomkaPoMutacji = (int) Math.round(obliczWartoscFunkcji(a, b, c, tymczasPotomekPoZamianie));
                    wartosciFunkcjiDlaPotomkowPoMutacji.add(wartoscFunkcjiPotomkaPoMutacji);
                    wartosciArgumentowPotomkowPoMutacji.add(tymczasPotomekPoZamianie);
                }

                System.out.println("Wartości argumentów dla potomków po mutacji : " + wartosciArgumentowPotomkowPoMutacji);
                System.out.println();
                System.out.println("Wartości funkcji dla potomków po mutacji : " + wartosciFunkcjiDlaPotomkowPoMutacji);
                System.out.println();

                int najwiekszaWartoscFunkcji = Collections.max(wartosciFunkcjiDlaPotomkowPoMutacji);

                int numerChromosmu = wartosciFunkcjiDlaPotomkowPoMutacji.indexOf(najwiekszaWartoscFunkcji);

                int argumentNajwiekszejWartosciFunkcji = wartosciArgumentowPotomkowPoMutacji.get(numerChromosmu);

                System.out.println("Rozwiązaniem jest chromosom o największej wartości funkcji przystosowania czyli chromosom numer " + (numerChromosmu + 1) + ", którego argument wynosi " + argumentNajwiekszejWartosciFunkcji + " a wartość funkcji : " + najwiekszaWartoscFunkcji);

                listaMaxWartosciArgumentowPotomkowPoMutacji.add(argumentNajwiekszejWartosciFunkcji);
                listaMaxWartosciFunkcjiDlaPotomkowPoMutacji.add(najwiekszaWartoscFunkcji);

                if(n==1000)
                {
                    int najwiekszaWartoscFunkcjiProgramu = Collections.max(listaMaxWartosciFunkcjiDlaPotomkowPoMutacji);
                    int ind = listaMaxWartosciFunkcjiDlaPotomkowPoMutacji.indexOf(najwiekszaWartoscFunkcjiProgramu);
                    int argumentNajwiekszejWartosciFunkcjiProgramu = listaMaxWartosciArgumentowPotomkowPoMutacji.get(ind);
                    System.out.println("Rozwiązaniem całego programu jest potomek o wartości funkcji " +najwiekszaWartoscFunkcjiProgramu+ " i argumencie równym "+ argumentNajwiekszejWartosciFunkcjiProgramu);
                }
        }
    }
}

