package session;

public class SessaoUtilizador {
	
	    private static Long utilizadorId;
	    private static String email;
	    private static String tipoUtilizador;

	    public static Long getUtilizadorId() {
	        return utilizadorId;
	    }

	    public static void setUtilizadorId(Long id) {
	        utilizadorId = id;
	    }

	    public static String getEmail() {
	        return email;
	    }

	    public static void setEmail(String emailUser) {
	        email = emailUser;
	    }

	    public static String getTipoUtilizador() {
	        return tipoUtilizador;
	    }

	    public static void setTipoUtilizador(String tipo) {
	        tipoUtilizador = tipo;
	    }

	    public static void limpar() {
	        utilizadorId = null;
	        email = null;
	        tipoUtilizador = null;
	    }
}


