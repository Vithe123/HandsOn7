import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AlgoritmoGeneticoMultiVariable {
    private static int CantidadInd = 6;
    private static int CantidadIte = 1000000;
    private static int generacion = 0;
    private static ArrayList<Individuo> poblacionA;
    private static ArrayList<Individuo> poblacionB;
    private static ArrayList<Individuo> poblacionC;
    private static ArrayList<Individuo> poblacionD;
    private static ArrayList<Individuo> poblacionE;
    private static ArrayList<Individuo> poblacionF;
    private static ArrayList<Individuo> hijosA;
    private static ArrayList<Individuo> hijosB;
    private static ArrayList<Individuo> hijosC;
    private static ArrayList<Individuo> hijosD;
    private static ArrayList<Individuo> hijosE;
    private static ArrayList<Individuo> hijosF;
    

    public static class Individuo
    {
        private long Genoma;
        private float Puntaje;
        private float Probabilidad;
        private float ProbabilidadAcumulada;

        public String getGenoma()
        {
            return DecimalToBinario(Genoma);
        }

        public void setGenoma(StringBuilder n)
        {
            this.Genoma = BinarioToDecimal(String.valueOf(n));
        }

        public String DecimalToBinario(long num)
        {
            if(num<0) //absoluto
            {
                num = num * -1;
            }
            long n = num;
            String bin = "";

            if(n == 0)
            {
                return "00000000";
            }

            while(n>0)
            {
                if(n%2 == 0)
                    bin = "0" + bin;
                else
                    bin = "1" + bin;
                n = n/2;
            }

            while (bin.length() < 8) //FORZAR 8 bits
            {
                bin = "0" + bin;
            }

            return bin;
        }

        public long BinarioToDecimal(String binario)
        {
            long decimal = 0;
            int posicion = 0;
            // Recorrer la cadena...
            for (int x = binario.length() - 1; x >= 0; x--) {
                // Saber si es 1 o 0; primero asumimos que es 1 y abajo comprobamos
                short digito = 1;
                if (binario.charAt(x) == '0') {
                    digito = 0;
                }

              /*
                  Se multiplica el dÃ­gito por 2 elevado a la potencia
                  segÃºn la posiciÃ³n; comenzando en 0, luego 1 y asÃ­
                  sucesivamente
               */
                double multiplicador = Math.pow(2, posicion);
                decimal += digito * multiplicador;
                posicion++;
            }
            return decimal;
        }
    }

    public static int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public static void CrearPoblacion()
    {
        poblacionA = new ArrayList<>();
        poblacionB = new ArrayList<>();
        poblacionC = new ArrayList<>();
        poblacionD = new ArrayList<>();
        poblacionE = new ArrayList<>();
        poblacionF = new ArrayList<>();
        hijosA = new ArrayList<>();
        hijosB = new ArrayList<>();
        hijosC = new ArrayList<>();
        hijosD = new ArrayList<>();
        hijosE = new ArrayList<>();
        hijosF = new ArrayList<>();
        for (int i = 0; i < CantidadInd; i++)
        {
            for(int j=0; j<=6;j++) {
                Individuo ind = new Individuo();
                if(j==0){
                    ind.Genoma = getRandomNumberUsingNextInt(1, 255);
                    poblacionA.add(ind);
                } else if(j==1) {
                    ind.Genoma = getRandomNumberUsingNextInt(1, 255);
                    poblacionB.add(ind);
                } else if(j==2) {
                    ind.Genoma = getRandomNumberUsingNextInt(1, 255);
                    poblacionC.add(ind);
                } else if(j==3) {
                    ind.Genoma = getRandomNumberUsingNextInt(1, 255);
                    poblacionD.add(ind);
                } else if(j==4) {
                    ind.Genoma = getRandomNumberUsingNextInt(1, 255);
                    poblacionE.add(ind);
                } else if(j==6) {
                    ind.Genoma = getRandomNumberUsingNextInt(1, 255);
                    poblacionF.add(ind);
                }
            }
        }
    }
    /*
    public static void Organizar(){
        //Metodo de burbuja
        boolean sw = false;
        while (!sw)
        {
            sw = true;
            for (int i = 1; i < poblacion.size(); i++)
            {
                if (poblacion.get(i).Puntaje > poblacion.get(i - 1).Puntaje)
                {
                    Individuo ind = poblacion.get(i);
                    poblacion.set(i, poblacion.get(i - 1));
                    poblacion.set(i - 1, ind);
                    sw = false;
                }
            }
        }
    }
    */

    static float MiFuncion(int contador)
    {
        float resultado;
        resultado = poblacionA.get(contador).Genoma + (2 * poblacionB.get(contador).Genoma) -
                (3 * poblacionC.get(contador).Genoma) + poblacionD.get(contador).Genoma +
                (4 * poblacionE.get(contador).Genoma) + poblacionF.get(contador).Genoma;
        return resultado;
    }

    static double Fitness(boolean mayor, float x)
    {
        if(mayor == false)
        {
            return x/30;
        }
        else
        {
            return 30/x;
        }
    }

    public static void DeterminarPuntajes(){
        boolean mayor = true;
        for (int i = 0; i < CantidadInd; i++)
        {
            float resFuncion = MiFuncion(i);
            mayor = true;
            if(resFuncion < 30)
                mayor = false;

            Individuo ind = poblacionA.get(i);
            ind.Puntaje = (float) (Fitness(mayor, resFuncion));
            poblacionA.set(i, ind);

            Individuo indB = poblacionB.get(i);
            indB.Puntaje = poblacionA.get(i).Puntaje;
            poblacionB.set(i, indB);

            Individuo indC = poblacionC.get(i);
            indC.Puntaje = poblacionA.get(i).Puntaje;
            poblacionC.set(i, indC);

            Individuo indD = poblacionD.get(i);
            indD.Puntaje = poblacionA.get(i).Puntaje;
            poblacionD.set(i, indD);

            Individuo indE = poblacionE.get(i);
            indE.Puntaje = poblacionA.get(i).Puntaje;
            poblacionE.set(i, indE);

            Individuo indF = poblacionF.get(i);
            indF.Puntaje = poblacionA.get(i).Puntaje;
            poblacionF.set(i, indF);
        }
    }


    public static void Mostrar()
    {
        System.out.println("Generacion no. " + generacion);
        System.out.print("\nGeneracion A: ");
        for (int i = 0; i < CantidadInd; i++)
        {
            System.out.print("(" + i + ")" + poblacionA.get(i).Genoma + " p:" + poblacionA.get(i).Puntaje + "   ");
        }
        System.out.print("\nGeneracion B: ");
        for (int i = 0; i < CantidadInd; i++)
        {
            System.out.print("(" + i + ")" + poblacionB.get(i).Genoma + " p:" + poblacionB.get(i).Puntaje + "   ");
        }
        System.out.print("\nGeneracion C: ");
        for (int i = 0; i < CantidadInd; i++)
        {
            System.out.print("(" + i + ")" + poblacionC.get(i).Genoma + " p:" + poblacionC.get(i).Puntaje + "   ");
        }
        System.out.print("\nGeneracion D: ");
        for (int i = 0; i < CantidadInd; i++)
        {
            System.out.print("(" + i + ")" + poblacionD.get(i).Genoma + " p:" + poblacionD.get(i).Puntaje + "   ");
        }
        System.out.print("\nGeneracion E: ");
        for (int i = 0; i < CantidadInd; i++)
        {
            System.out.print("(" + i + ")" + poblacionE.get(i).Genoma + " p:" + poblacionE.get(i).Puntaje + "   ");
        }
        System.out.print("\nGeneracion F: ");
        for (int i = 0; i < CantidadInd; i++)
        {
            System.out.print("(" + i + ")" + poblacionF.get(i).Genoma + " p:" + poblacionF.get(i).Puntaje + "   ");
        }
        System.out.print("\n\n");
        generacion++;
    }

    static void Combinacion()
    {
        float puntaje, puntajeAcumA = 0, puntajeAcumB = 0, puntajeAcumC = 0, puntajeAcumD = 0, puntajeAcumE = 0, puntajeAcumF = 0, puntajeAcumInd = 0,
                buffA = 0, buffB = 0, buffC = 0, buffD = 0, buffE = 0, buffF = 0;

        //se determina el acumulado del porcentaje
        for(int i = 0; i < CantidadInd; i++)
        {
            puntaje = poblacionA.get(i).Puntaje;
            puntajeAcumA = puntajeAcumA + puntaje;
            puntaje = poblacionB.get(i).Puntaje;
            puntajeAcumB = puntajeAcumB + puntaje;
            puntaje = poblacionC.get(i).Puntaje;
            puntajeAcumC = puntajeAcumC + puntaje;
            puntaje = poblacionD.get(i).Puntaje;
            puntajeAcumD = puntajeAcumD + puntaje;
            puntaje = poblacionE.get(i).Puntaje;
            puntajeAcumE = puntajeAcumE + puntaje;
            puntaje = poblacionF.get(i).Puntaje;
            puntajeAcumF = puntajeAcumF + puntaje;
        }

        for(int i = 0; i < CantidadInd; i++)
        {
            Individuo indA = poblacionA.get(i);
            puntaje = poblacionA.get(i).Puntaje;
            puntajeAcumInd = puntaje/puntajeAcumA;
            indA.Probabilidad = puntajeAcumInd;
            buffA = puntajeAcumInd + buffA;
            indA.ProbabilidadAcumulada = buffA;
            poblacionA.set(i,indA);

            Individuo indB = poblacionB.get(i);
            puntaje = poblacionB.get(i).Puntaje;
            puntajeAcumInd = puntaje/puntajeAcumB;
            indB.Probabilidad = puntajeAcumInd;
            buffB = puntajeAcumInd + buffB;
            indB.ProbabilidadAcumulada = buffB;
            poblacionB.set(i,indB);

            Individuo indC = poblacionC.get(i);
            puntaje = poblacionC.get(i).Puntaje;
            puntajeAcumInd = puntaje/puntajeAcumC;
            indC.Probabilidad = puntajeAcumInd;
            buffC = puntajeAcumInd + buffC;
            indC.ProbabilidadAcumulada = buffC;
            poblacionC.set(i,indC);

            Individuo indD = poblacionD.get(i);
            puntaje = poblacionD.get(i).Puntaje;
            puntajeAcumInd = puntaje/puntajeAcumD;
            indD.Probabilidad = puntajeAcumInd;
            buffD = puntajeAcumInd + buffD;
            indD.ProbabilidadAcumulada = buffD;
            poblacionD.set(i,indD);

            Individuo indE = poblacionE.get(i);
            puntaje = poblacionE.get(i).Puntaje;
            puntajeAcumInd = puntaje/puntajeAcumE;
            indE.Probabilidad = puntajeAcumInd;
            buffE = puntajeAcumInd + buffE;
            indE.ProbabilidadAcumulada = buffE;
            poblacionE.set(i,indE);

            Individuo indF = poblacionF.get(i);
            puntaje = poblacionF.get(i).Puntaje;
            puntajeAcumInd = puntaje/puntajeAcumF;
            indF.Probabilidad = puntajeAcumInd;
            buffF = puntajeAcumInd + buffF;
            indF.ProbabilidadAcumulada = buffF;
            poblacionF.set(i,indF);
        }

        Random r = new Random();
        float A_RandRuletaNum = 0 + r.nextFloat() * (poblacionA.get(poblacionA.size()-1).ProbabilidadAcumulada - 0);
        float A_RandRuletaNum2 = 0 + r.nextFloat() * (poblacionA.get(poblacionA.size()-1).ProbabilidadAcumulada - 0);
        float B_RandRuletaNum = 0 + r.nextFloat() * (poblacionB.get(poblacionB.size()-1).ProbabilidadAcumulada - 0);
        float B_RandRuletaNum2 = 0 + r.nextFloat() * (poblacionB.get(poblacionB.size()-1).ProbabilidadAcumulada - 0);
        float C_RandRuletaNum = 0 + r.nextFloat() * (poblacionC.get(poblacionC.size()-1).ProbabilidadAcumulada - 0);
        float C_RandRuletaNum2 = 0 + r.nextFloat() * (poblacionC.get(poblacionC.size()-1).ProbabilidadAcumulada - 0);
        float D_RandRuletaNum = 0 + r.nextFloat() * (poblacionD.get(poblacionD.size()-1).ProbabilidadAcumulada - 0);
        float D_RandRuletaNum2 = 0 + r.nextFloat() * (poblacionD.get(poblacionD.size()-1).ProbabilidadAcumulada - 0);
        float E_RandRuletaNum = 0 + r.nextFloat() * (poblacionE.get(poblacionE.size()-1).ProbabilidadAcumulada - 0);
        float E_RandRuletaNum2 = 0 + r.nextFloat() * (poblacionE.get(poblacionE.size()-1).ProbabilidadAcumulada - 0);
        float F_RandRuletaNum = 0 + r.nextFloat() * (poblacionF.get(poblacionF.size()-1).ProbabilidadAcumulada - 0);
        float F_RandRuletaNum2 = 0 + r.nextFloat() * (poblacionF.get(poblacionF.size()-1).ProbabilidadAcumulada - 0);

        float A_bufferRuleta = 0, probabilidadActual=0;
        float B_bufferRuleta = 0;
        float C_bufferRuleta = 0;
        float D_bufferRuleta = 0;
        float E_bufferRuleta = 0;
        float F_bufferRuleta = 0;

        long temporal;

        String A_Padre1 = "00000000";
        String A_Padre2 = "00000000";
        String B_Padre1 = "00000000";
        String B_Padre2 = "00000000";
        String C_Padre1 = "00000000";
        String C_Padre2 = "00000000";
        String D_Padre1 = "00000000";
        String D_Padre2 = "00000000";
        String E_Padre1 = "00000000";
        String E_Padre2 = "00000000";
        String F_Padre1 = "00000000";
        String F_Padre2 = "00000000";

        boolean A_Padre1Listo = false;
        boolean A_Padre2Listo = false;
        boolean B_Padre1Listo = false;
        boolean B_Padre2Listo = false;
        boolean C_Padre1Listo = false;
        boolean C_Padre2Listo = false;
        boolean D_Padre1Listo = false;
        boolean D_Padre2Listo = false;
        boolean E_Padre1Listo = false;
        boolean E_Padre2Listo = false;
        boolean F_Padre1Listo = false;
        boolean F_Padre2Listo = false;

        for(int i = 0; i < CantidadInd; i++)
        {
            Individuo indA = poblacionA.get(i);
            Individuo indB = poblacionB.get(i);
            Individuo indC = poblacionC.get(i);
            Individuo indD = poblacionD.get(i);
            Individuo indE = poblacionE.get(i);
            Individuo indF = poblacionF.get(i);

            probabilidadActual = poblacionA.get(i).ProbabilidadAcumulada;
            if((A_bufferRuleta<A_RandRuletaNum && A_RandRuletaNum<=probabilidadActual) && A_Padre1Listo == false) {
                temporal = poblacionA.get(i).Genoma;
                A_Padre1 = indA.DecimalToBinario(temporal);
                A_Padre1Listo = true;
            } else {
                if((A_bufferRuleta<A_RandRuletaNum2 && A_RandRuletaNum2<=probabilidadActual) && A_Padre2Listo == false) {
                    temporal = poblacionA.get(i).Genoma;
                    A_Padre2 = indA.DecimalToBinario(temporal);
                    A_Padre1Listo = true;
                }
                else {
                    A_bufferRuleta = probabilidadActual;
                }
            }

            probabilidadActual = poblacionB.get(i).ProbabilidadAcumulada;
            if((B_bufferRuleta<B_RandRuletaNum && B_RandRuletaNum<=probabilidadActual) && B_Padre1Listo == false) {
                temporal = poblacionB.get(i).Genoma;
                B_Padre1 = indB.DecimalToBinario(temporal);
                B_Padre1Listo = true;
            } else {
                if((B_bufferRuleta<B_RandRuletaNum2 && B_RandRuletaNum2<=probabilidadActual) && B_Padre2Listo == false) {
                    temporal = poblacionB.get(i).Genoma;
                    B_Padre2 = indB.DecimalToBinario(temporal);
                    B_Padre1Listo = true;
                }
                else {
                    B_bufferRuleta = probabilidadActual;
                }
            }

            probabilidadActual = poblacionC.get(i).ProbabilidadAcumulada;
            if((C_bufferRuleta<C_RandRuletaNum && C_RandRuletaNum<=probabilidadActual) && C_Padre1Listo == false) {
                temporal = poblacionC.get(i).Genoma;
                C_Padre1 = indC.DecimalToBinario(temporal);
                C_Padre1Listo = true;
            } else {
                if((C_bufferRuleta<C_RandRuletaNum2 && C_RandRuletaNum2<=probabilidadActual) && C_Padre2Listo == false) {
                    temporal = poblacionC.get(i).Genoma;
                    C_Padre2 = indC.DecimalToBinario(temporal);
                    C_Padre1Listo = true;
                }
                else {
                    C_bufferRuleta = probabilidadActual;
                }
            }

            probabilidadActual = poblacionD.get(i).ProbabilidadAcumulada;
            if((D_bufferRuleta<D_RandRuletaNum && D_RandRuletaNum<=probabilidadActual) && D_Padre1Listo == false) {
                temporal = poblacionD.get(i).Genoma;
                D_Padre1 = indD.DecimalToBinario(temporal);
                D_Padre1Listo = true;
            } else {
                if((D_bufferRuleta<D_RandRuletaNum2 && D_RandRuletaNum2<=probabilidadActual) && D_Padre2Listo == false) {
                    temporal = poblacionD.get(i).Genoma;
                    D_Padre2 = indD.DecimalToBinario(temporal);
                    D_Padre1Listo = true;
                }
                else {
                    D_bufferRuleta = probabilidadActual;
                }
            }

            probabilidadActual = poblacionE.get(i).ProbabilidadAcumulada;
            if((E_bufferRuleta<E_RandRuletaNum && E_RandRuletaNum<=probabilidadActual) && E_Padre1Listo == false) {
                temporal = poblacionE.get(i).Genoma;
                E_Padre1 = indE.DecimalToBinario(temporal);
                E_Padre1Listo = true;
            } else {
                if((E_bufferRuleta<E_RandRuletaNum2 && E_RandRuletaNum2<=probabilidadActual) && E_Padre2Listo == false) {
                    temporal = poblacionE.get(i).Genoma;
                    E_Padre2 = indE.DecimalToBinario(temporal);
                    E_Padre1Listo = true;
                }
                else {
                    E_bufferRuleta = probabilidadActual;
                }
            }

            probabilidadActual = poblacionF.get(i).ProbabilidadAcumulada;
            if((F_bufferRuleta<F_RandRuletaNum && F_RandRuletaNum<=probabilidadActual) && F_Padre1Listo == false) {
                temporal = poblacionF.get(i).Genoma;
                F_Padre1 = indF.DecimalToBinario(temporal);
                F_Padre1Listo = true;
            } else {
                if((F_bufferRuleta<F_RandRuletaNum2 && F_RandRuletaNum2<=probabilidadActual) && F_Padre2Listo == false) {
                    temporal = poblacionF.get(i).Genoma;
                    F_Padre2 = indF.DecimalToBinario(temporal);
                    F_Padre1Listo = true;
                }
                else {
                    F_bufferRuleta = probabilidadActual;
                }
            }
        }
        
        int contador;
        
        StringBuilder A_Hijo1 = new StringBuilder(A_Padre1.length());
        StringBuilder A_Hijo2 = new StringBuilder(A_Padre1.length());
        int mutacion = getRandomNumberUsingNextInt(0,A_Padre1.length());
        for(contador = 0; contador < mutacion; contador++)
        {
            A_Hijo1.append(A_Padre2.charAt(contador));
            A_Hijo2.append(A_Padre1.charAt(contador));
        }
        for(int cont2 = contador; cont2 < A_Padre1.length(); cont2++)
        {
            A_Hijo1.append(A_Padre1.charAt(cont2));
            try {
                A_Hijo2.append(A_Padre2.charAt(cont2));
            } catch(Exception e)
            {
                System.out.println("A_ Hijo1: " + A_Hijo1);
                System.out.println("A_ Hijo1 TAM: " + A_Hijo1.length());
                System.out.println("A_ Hijo2: " + A_Hijo2);
                System.out.println("A_ Hijo2 TAM: " + A_Hijo2.length());
                System.out.println("A_ Padre1: " + A_Padre1);
                System.out.println("A_ Padre1 TAM: " + A_Padre1.length());
                System.out.println("A_ Padre2: " + A_Padre2);
                System.out.println("A_ Padre2 TAM: " + A_Padre2.length());
            }
        }

        StringBuilder B_Hijo1 = new StringBuilder(B_Padre1.length());
        StringBuilder B_Hijo2 = new StringBuilder(B_Padre1.length());
        mutacion = getRandomNumberUsingNextInt(0,B_Padre1.length());
        for(contador = 0; contador < mutacion; contador++)
        {
            B_Hijo1.append(B_Padre2.charAt(contador));
            B_Hijo2.append(B_Padre1.charAt(contador));
        }
        for(int cont2 = contador; cont2 < B_Padre1.length(); cont2++)
        {
            B_Hijo1.append(B_Padre1.charAt(cont2));
            try {
                B_Hijo2.append(B_Padre2.charAt(cont2));
            } catch(Exception e)
            {
                System.out.println("B_ Hijo1: " + B_Hijo1);
                System.out.println("B_ Hijo1 TAM: " + B_Hijo1.length());
                System.out.println("B_ Hijo2: " + B_Hijo2);
                System.out.println("B_ Hijo2 TAM: " + B_Hijo2.length());
                System.out.println("B_ Padre1: " + B_Padre1);
                System.out.println("B_ Padre1 TAM: " + B_Padre1.length());
                System.out.println("B_ Padre2: " + B_Padre2);
                System.out.println("B_ Padre2 TAM: " + B_Padre2.length());
            }
        }

        StringBuilder C_Hijo1 = new StringBuilder(C_Padre1.length());
        StringBuilder C_Hijo2 = new StringBuilder(C_Padre1.length());
        mutacion = getRandomNumberUsingNextInt(0,C_Padre1.length());
        for(contador = 0; contador < mutacion; contador++)
        {
            C_Hijo1.append(C_Padre2.charAt(contador));
            C_Hijo2.append(C_Padre1.charAt(contador));
        }
        for(int cont2 = contador; cont2 < C_Padre1.length(); cont2++)
        {
            C_Hijo1.append(C_Padre1.charAt(cont2));
            try {
                C_Hijo2.append(C_Padre2.charAt(cont2));
            } catch(Exception e)
            {
                System.out.println("C_ Hijo1: " + C_Hijo1);
                System.out.println("C_ Hijo1 TAM: " + C_Hijo1.length());
                System.out.println("C_ Hijo2: " + C_Hijo2);
                System.out.println("C_ Hijo2 TAM: " + C_Hijo2.length());
                System.out.println("C_ Padre1: " + C_Padre1);
                System.out.println("C_ Padre1 TAM: " + C_Padre1.length());
                System.out.println("C_ Padre2: " + C_Padre2);
                System.out.println("C_ Padre2 TAM: " + C_Padre2.length());
            }
        }

        StringBuilder D_Hijo1 = new StringBuilder(D_Padre1.length());
        StringBuilder D_Hijo2 = new StringBuilder(D_Padre1.length());
        mutacion = getRandomNumberUsingNextInt(0,D_Padre1.length());
        for(contador = 0; contador < mutacion; contador++)
        {
            D_Hijo1.append(D_Padre2.charAt(contador));
            D_Hijo2.append(D_Padre1.charAt(contador));
        }
        for(int cont2 = contador; cont2 < D_Padre1.length(); cont2++)
        {
            D_Hijo1.append(D_Padre1.charAt(cont2));
            try {
                D_Hijo2.append(D_Padre2.charAt(cont2));
            } catch(Exception e)
            {
                System.out.println("D_ Hijo1: " + D_Hijo1);
                System.out.println("D_ Hijo1 TAM: " + D_Hijo1.length());
                System.out.println("D_ Hijo2: " + D_Hijo2);
                System.out.println("D_ Hijo2 TAM: " + D_Hijo2.length());
                System.out.println("D_ Padre1: " + D_Padre1);
                System.out.println("D_ Padre1 TAM: " + D_Padre1.length());
                System.out.println("D_ Padre2: " + D_Padre2);
                System.out.println("D_ Padre2 TAM: " + D_Padre2.length());
            }
        }

        StringBuilder E_Hijo1 = new StringBuilder(E_Padre1.length());
        StringBuilder E_Hijo2 = new StringBuilder(E_Padre1.length());
        mutacion = getRandomNumberUsingNextInt(0,E_Padre1.length());
        for(contador = 0; contador < mutacion; contador++)
        {
            E_Hijo1.append(E_Padre2.charAt(contador));
            E_Hijo2.append(E_Padre1.charAt(contador));
        }
        for(int cont2 = contador; cont2 < E_Padre1.length(); cont2++)
        {
            E_Hijo1.append(E_Padre1.charAt(cont2));
            try {
                E_Hijo2.append(E_Padre2.charAt(cont2));
            } catch(Exception e)
            {
                System.out.println("E_ Hijo1: " + E_Hijo1);
                System.out.println("E_ Hijo1 TAM: " + E_Hijo1.length());
                System.out.println("E_ Hijo2: " + E_Hijo2);
                System.out.println("E_ Hijo2 TAM: " + E_Hijo2.length());
                System.out.println("E_ Padre1: " + E_Padre1);
                System.out.println("E_ Padre1 TAM: " + E_Padre1.length());
                System.out.println("E_ Padre2: " + E_Padre2);
                System.out.println("E_ Padre2 TAM: " + E_Padre2.length());
            }
        }

        StringBuilder F_Hijo1 = new StringBuilder(F_Padre1.length());
        StringBuilder F_Hijo2 = new StringBuilder(F_Padre1.length());
        mutacion = getRandomNumberUsingNextInt(0,F_Padre1.length());
        for(contador = 0; contador < mutacion; contador++)
        {
            F_Hijo1.append(F_Padre2.charAt(contador));
            F_Hijo2.append(F_Padre1.charAt(contador));
        }
        for(int cont2 = contador; cont2 < F_Padre1.length(); cont2++)
        {
            F_Hijo1.append(F_Padre1.charAt(cont2));
            try {
                F_Hijo2.append(F_Padre2.charAt(cont2));
            } catch(Exception e)
            {
                System.out.println("F_ Hijo1: " + F_Hijo1);
                System.out.println("F_ Hijo1 TAM: " + F_Hijo1.length());
                System.out.println("F_ Hijo2: " + F_Hijo2);
                System.out.println("F_ Hijo2 TAM: " + F_Hijo2.length());
                System.out.println("F_ Padre1: " + F_Padre1);
                System.out.println("F_ Padre1 TAM: " + F_Padre1.length());
                System.out.println("F_ Padre2: " + F_Padre2);
                System.out.println("F_ Padre2 TAM: " + F_Padre2.length());
            }
        }
        
        Individuo A_ind = new Individuo();
        A_ind.Genoma = A_ind.BinarioToDecimal(String.valueOf(A_Hijo1)); 
        hijosA.add(A_ind);
        Individuo A_ind2 = new Individuo();
        A_ind2.Genoma = A_ind2.BinarioToDecimal(String.valueOf(A_Hijo2)); 
        hijosA.add(A_ind2);

        Individuo B_ind = new Individuo();
        B_ind.Genoma = B_ind.BinarioToDecimal(String.valueOf(B_Hijo1)); 
        hijosB.add(B_ind);
        Individuo B_ind2 = new Individuo();
        B_ind2.Genoma = B_ind2.BinarioToDecimal(String.valueOf(B_Hijo2)); 
        hijosB.add(B_ind2);

        Individuo C_ind = new Individuo();
        C_ind.Genoma = C_ind.BinarioToDecimal(String.valueOf(C_Hijo1)); 
        hijosC.add(C_ind);
        Individuo C_ind2 = new Individuo();
        C_ind2.Genoma = C_ind2.BinarioToDecimal(String.valueOf(C_Hijo2)); 
        hijosC.add(C_ind2);

        Individuo D_ind = new Individuo();
        D_ind.Genoma = D_ind.BinarioToDecimal(String.valueOf(D_Hijo1)); 
        hijosD.add(D_ind);
        Individuo D_ind2 = new Individuo();
        D_ind2.Genoma = D_ind2.BinarioToDecimal(String.valueOf(D_Hijo2)); 
        hijosD.add(D_ind2);

        Individuo E_ind = new Individuo();
        E_ind.Genoma = E_ind.BinarioToDecimal(String.valueOf(E_Hijo1)); 
        hijosE.add(E_ind);
        Individuo E_ind2 = new Individuo();
        E_ind2.Genoma = E_ind2.BinarioToDecimal(String.valueOf(E_Hijo2)); 
        hijosE.add(E_ind2);

        Individuo F_ind = new Individuo();
        F_ind.Genoma = F_ind.BinarioToDecimal(String.valueOf(F_Hijo1)); 
        hijosF.add(F_ind);
        Individuo F_ind2 = new Individuo();
        F_ind2.Genoma = F_ind2.BinarioToDecimal(String.valueOf(F_Hijo2)); 
        hijosF.add(F_ind2);
    }


    static void Mutacion(){
        long GenomaHijo;
        StringBuilder SGenoma;
        float ratioMutacion = 0.01f;
        Random r = new Random();
        for(int i=0; i< CantidadInd; i++)
        {
            float randRatioMutacion = 0 + r.nextFloat() * (1 - 0);
            if(randRatioMutacion < ratioMutacion)
            {
                Individuo indA = hijosA.get(i);
                GenomaHijo = hijosA.get(i).Genoma;
                SGenoma = new StringBuilder(indA.DecimalToBinario(GenomaHijo));
                int rand = getRandomNumberUsingNextInt(0, SGenoma.length());
                char bit = SGenoma.charAt(rand);
                if (bit == '1') {
                    SGenoma.setCharAt(rand, '0');
                } else {
                    SGenoma.setCharAt(rand, '1');
                }
                indA.Genoma = indA.BinarioToDecimal(String.valueOf(SGenoma)); //B0 255
            }

            randRatioMutacion = 0 + r.nextFloat() * (1 - 0);
            if(randRatioMutacion < ratioMutacion)
            {
                Individuo indB = hijosB.get(i);
                GenomaHijo = hijosB.get(i).Genoma;
                SGenoma = new StringBuilder(indB.DecimalToBinario(GenomaHijo));
                int rand = getRandomNumberUsingNextInt(0, SGenoma.length());
                char bit = SGenoma.charAt(rand);
                if (bit == '1') {
                    SGenoma.setCharAt(rand, '0');
                } else {
                    SGenoma.setCharAt(rand, '1');
                }
                indB.Genoma = indB.BinarioToDecimal(String.valueOf(SGenoma)); //B0 255
            }

            randRatioMutacion = 0 + r.nextFloat() * (1 - 0);
            if(randRatioMutacion < ratioMutacion)
            {
                Individuo indC = hijosC.get(i);
                GenomaHijo = hijosC.get(i).Genoma;
                SGenoma = new StringBuilder(indC.DecimalToBinario(GenomaHijo));
                int rand = getRandomNumberUsingNextInt(0, SGenoma.length());
                char bit = SGenoma.charAt(rand);
                if (bit == '1') {
                    SGenoma.setCharAt(rand, '0');
                } else {
                    SGenoma.setCharAt(rand, '1');
                }
                indC.Genoma = indC.BinarioToDecimal(String.valueOf(SGenoma)); //B0 255
            }

            randRatioMutacion = 0 + r.nextFloat() * (1 - 0);
            if(randRatioMutacion < ratioMutacion)
            {
                Individuo indD = hijosD.get(i);
                GenomaHijo = hijosD.get(i).Genoma;
                SGenoma = new StringBuilder(indD.DecimalToBinario(GenomaHijo));
                int rand = getRandomNumberUsingNextInt(0, SGenoma.length());
                char bit = SGenoma.charAt(rand);
                if (bit == '1') {
                    SGenoma.setCharAt(rand, '0');
                } else {
                    SGenoma.setCharAt(rand, '1');
                }
                indD.Genoma = indD.BinarioToDecimal(String.valueOf(SGenoma)); //B0 255
            }

            randRatioMutacion = 0 + r.nextFloat() * (1 - 0);
            if(randRatioMutacion < ratioMutacion)
            {
                Individuo indE = hijosE.get(i);
                GenomaHijo = hijosE.get(i).Genoma;
                SGenoma = new StringBuilder(indE.DecimalToBinario(GenomaHijo));
                int rand = getRandomNumberUsingNextInt(0, SGenoma.length());
                char bit = SGenoma.charAt(rand);
                if (bit == '1') {
                    SGenoma.setCharAt(rand, '0');
                } else {
                    SGenoma.setCharAt(rand, '1');
                }
                indE.Genoma = indE.BinarioToDecimal(String.valueOf(SGenoma)); //B0 255
            }

            randRatioMutacion = 0 + r.nextFloat() * (1 - 0);
            if(randRatioMutacion < ratioMutacion)
            {
                Individuo indF = hijosF.get(i);
                GenomaHijo = hijosF.get(i).Genoma;
                SGenoma = new StringBuilder(indF.DecimalToBinario(GenomaHijo));
                int rand = getRandomNumberUsingNextInt(0, SGenoma.length());
                char bit = SGenoma.charAt(rand);
                if (bit == '1') {
                    SGenoma.setCharAt(rand, '0');
                } else {
                    SGenoma.setCharAt(rand, '1');
                }
                indF.Genoma = indF.BinarioToDecimal(String.valueOf(SGenoma)); //B0 255
            }
        }
    }

    static void ReemplazarPoblacion()
    {
        poblacionA = new ArrayList<>();
        poblacionB = new ArrayList<>();
        poblacionC = new ArrayList<>();
        poblacionD = new ArrayList<>();
        poblacionE = new ArrayList<>();
        poblacionF = new ArrayList<>();
        for(int i = 0; i < CantidadInd; i++)
        {
            poblacionA.add(hijosA.get(i));
            poblacionB.add(hijosB.get(i));
            poblacionC.add(hijosC.get(i));
            poblacionD.add(hijosD.get(i));
            poblacionE.add(hijosE.get(i));
            poblacionF.add(hijosF.get(i));
        }
        hijosA = new ArrayList<>();
        hijosB = new ArrayList<>();
        hijosC = new ArrayList<>();
        hijosD = new ArrayList<>();
        hijosE = new ArrayList<>();
        hijosF = new ArrayList<>();
    }

    public static void Start(){
        CrearPoblacion();
        int ite = 0;
        boolean bandBreak = false;
        while(true)
        {
            DeterminarPuntajes();
            //Organizar();
            Mostrar();
            int j =0;
            while(j < poblacionA.size())
            {
                if(poblacionA.get(j).Puntaje == 1)
                {
                    bandBreak = true;
                }
                j++;
            }
            if(bandBreak == true)
            {
                break;
            }
            while (hijosA.size() < poblacionA.size()) {
                Combinacion();
            }
            Mutacion();
            ReemplazarPoblacion();
            ite++;
        }
        System.out.println("FIN  " + ite++);
    }

    public static void main(String[] args) {
        Start();
    }
}
