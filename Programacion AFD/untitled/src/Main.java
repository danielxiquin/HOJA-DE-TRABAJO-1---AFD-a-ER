
public class Main {

    public static final int ACCEPTED = 0;
    public static final int ERROR = 1;

    public static int stringIsAccepted(String[] value) {
        AFD automata = new AFD();

        if (automata.parse(value))
            return ACCEPTED;
        else
            return ERROR;
    }

    public static void main(String[] args) {
        // Ejemplo de cadena de entrada
        String[] input = {"var", "id", ":", "int", "=", "data"};

        int result = stringIsAccepted(input);

        if (result == ACCEPTED) {
            System.out.println("Cadena aceptada.");
        } else {
            System.out.println("Cadena rechazada.");
        }
    }

    // Clase interna AFD
    static class AFD {

        private int[] acceptStates = {6, 8};

        public boolean parse(String[] input) {
            boolean isAccepted = false;
            boolean thereIsError = false;
            int actualState = 0;
            int index = 0;

            while (!isAccepted && !thereIsError) {
                String token = input[index];

                switch (actualState) {
                    case 0:
                        try {
                            actualState = state0(token);
                        } catch (Exception e) {
                            thereIsError = true;
                        }
                        break;

                    case 1:
                        try {
                            actualState = state1(token);
                        } catch (Exception e) {
                            thereIsError = true;

                        }
                        break;

                    case 2:
                        try {
                            actualState = state2(token);
                        } catch (Exception e) {
                            thereIsError = true;
                        }
                        break;

                    case 3:
                        try {
                            actualState = state3(token);
                        } catch (Exception e) {
                            thereIsError = true;
                        }
                        break;

                    default:
                        thereIsError = true;
                        break;
                }

                index++;

                if (index == input.length) {
                    isAccepted = isAcceptState(actualState);

                    thereIsError = !isAccepted;
                }
            }
            return isAccepted;
        }

        private boolean isAcceptState(int state) {
            for (int statetested : acceptStates) {
                if (statetested == state)
                    return true;
            }

            return false;
        }

        private int state0(String token) throws Exception {
            if (token.equals("val")) {
                return 1;
            } else if (token.equals("id")) {
                return 2;
            } else if (token.equals("var")) {
                return 3;
            } else {
                throw new Exception("No se esperaba ese token: " + token);
            }
        }

        private int state1(String token) throws Exception {
            if (token.equals("id")) {
                return 4;
            } else {
                throw new Exception("No se esperaba ese token: " + token);
            }
        }

        private int state2(String token) throws Exception {
            if (token.equals(":")) {
                return 5;
            } else if (token.equals("=")) {
                return 8;
            } else {
                throw new Exception("No se esperaba ese token" + token);
            }
        }

        private int state3(String token) throws Exception {
            if (token.equals("id")) {
                return 2;
            } else {
                throw new Exception("No se esperaba ese token" + token);
            }
        }

        private int state4(String token) throws Exception {
            if (token.equals(":")) {
                return 5;
            } else if (token.equals("=")) {
                return 6;
            } else {
                throw new Exception("No se esperaba ese token" + token);
            }
        }

        private int state5(String token) throws Exception {
            if (token.equals("int")) {
                return 7;
            } else if (token.equals("str")) {
                return 7;
            } else {
                throw new Exception("No se esperaba ese token: " + token);
            }
        }

        private int state6(String token) throws Exception {
            if (token.equals("data")) {
                return 8;
            } else {
                throw new Exception("No se esperaba ese token: " + token);
            }
        }

        private int state7(String token) throws Exception {
            if (token.equals("=")) {
                return 8;
            } else {
                throw new Exception("No se esperaba ese token: " + token);
            }
        }

        private int state8(String token) throws Exception {
            if (token.equals("data")) {
                return 8;
            } else {
                throw new Exception("No se esperaba ese token: " + token);
            }
        }
    }
}
