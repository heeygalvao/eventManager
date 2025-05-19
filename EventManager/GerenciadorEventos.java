import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class GerenciadorEventos {
    private List<Evento> eventos;
    private List<Evento> confirmados;
    private static final String ARQUIVO = "events.data";

    public GerenciadorEventos() {
        eventos = new ArrayList<>();
        confirmados = new ArrayList<>();
        carregarEventos();
    }

    public void cadastrarEvento(Evento evento) {
        eventos.add(evento);
        salvarEventos();
    }

    public List<Evento> listarEventos() {
        Collections.sort(eventos);
        return eventos;
    }

    public void confirmarParticipacao(int indice) {
        if (indice >= 0 && indice < eventos.size()) {
            confirmados.add(eventos.get(indice));
        }
    }

    public List<Evento> listarConfirmados() {
        return confirmados;
    }

    public void cancelarParticipacao(int indice) {
        if (indice >= 0 && indice < confirmados.size()) {
            confirmados.remove(indice);
        }
    }

    private void salvarEventos() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Evento e : eventos) {
                bw.write(e.getNome() + ";" + e.getEndereco() + ";" + e.getCategoria() + ";" +
                        e.getHorario().toString() + ";" + e.getDescricao());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar eventos.");
        }
    }

    private void carregarEventos() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                Evento evento = new Evento(
                        partes[0],
                        partes[1],
                        partes[2],
                        LocalDateTime.parse(partes[3]),
                        partes[4]
                );
                eventos.add(evento);
            }
        } catch (IOException e) {
            System.out.println("Arquivo de eventos não encontrado.");
        }
    }
}
