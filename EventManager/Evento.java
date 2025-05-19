import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Evento implements Comparable<Evento> {
    private String nome;
    private String endereco;
    private String categoria;
    private LocalDateTime horario;
    private String descricao;

    public Evento(String nome, String endereco, String categoria, LocalDateTime horario, String descricao) {
        this.nome = nome;
        this.endereco = endereco;
        this.categoria = categoria;
        this.horario = horario;
        this.descricao = descricao;
    }

    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public String getCategoria() { return categoria; }
    public LocalDateTime getHorario() { return horario; }
    public String getDescricao() { return descricao; }

    @Override
    public int compareTo(Evento outro) {
        return this.horario.compareTo(outro.horario);
    }

    @Override
    public String toString() {
        return "[" + horario.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + "] " + nome + " - " + categoria + "\nEndereço: " + endereco + "\nDescrição: " + descricao;
    }

    public boolean isOcorrendoAgora() {
        LocalDateTime agora = LocalDateTime.now();
        return horario.isBefore(agora.plusMinutes(1)) && horario.isAfter(agora.minusMinutes(1));
    }

    public boolean isJaOcorrido() {
        return horario.isBefore(LocalDateTime.now());
    }
}
