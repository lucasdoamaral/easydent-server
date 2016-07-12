package teste;

public class Teste {

	public static void main(String[] args) {
		new Teste();
	}

	public Teste() {
		testeHorarios();
	}

	private void testeHorarios() {

		// List<String> horariosDisponiveis = new ArrayList<>();
		// HorarioDisponivel horario = new HorarioDisponivel();
		// horario.setHoraInicial(LocalTime.of(8, 30));
		// horario.setHoraAlmocoInicial(LocalTime.of(12, 0));
		// horario.setHoraAlmocoFinal(LocalTime.of(14, 0));
		// horario.setHoraFinal(LocalTime.of(20, 0));
		//
		// LocalTime horaInicial = horario.getHoraInicial();
		// LocalTime horaAlmocoInicial = horario.getHoraAlmocoInicial();
		//
		// while (horaInicial.isBefore(horaAlmocoInicial) &&
		// horaInicial.isBefore(horario.getHoraFinal())) {
		// horariosDisponiveis.add(horaInicial.format(DateTimeFormatter.ofPattern("HH:mm")));
		// horaInicial = horaInicial.plusMinutes(15);
		// }
		//
		// if (horario.getHoraAlmocoFinal() != null) {
		// horaInicial = horario.getHoraAlmocoFinal();
		// while (horaInicial.isBefore(horario.getHoraFinal())) {
		// horariosDisponiveis.add(horaInicial.format(DateTimeFormatter.ofPattern("HH:mm")));
		// horaInicial = horaInicial.plusMinutes(15);
		// }
		// }

		// System.out.println(horariosDisponiveis);
	}

}
