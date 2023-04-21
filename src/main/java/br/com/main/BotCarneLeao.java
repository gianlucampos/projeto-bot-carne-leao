package br.com.main;

import br.com.main.models.Dividend;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import dev.botcity.framework.bot.DesktopBot;
import dev.botcity.maestro_sdk.BotExecutor;
import dev.botcity.maestro_sdk.runner.BotExecution;
import dev.botcity.maestro_sdk.runner.RunnableAgent;
import lombok.SneakyThrows;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class BotCarneLeao extends DesktopBot implements RunnableAgent {

    private static final String PATH = "./src/resources/Reports.json";

    public BotCarneLeao() {
        try {
            setResourceClassLoader(this.getClass().getClassLoader());
            loadResources();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BotExecutor.run(new BotCarneLeao(), args);
    }

    @Override
    public void action(BotExecution botExecution) {
        List<Dividend> dividendList = getDividendsList();

        try {
            goToRendimentosPage();
            dividendList.forEach(this::adicionarRendimentos);
            goToPagamentosPage();
            dividendList.forEach(this::adicionarPagamento);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @SneakyThrows
    private List<Dividend> getDividendsList() {
        JsonReader reader = new JsonReader(new FileReader(PATH));
        Gson gson = new Gson();
        return gson.fromJson(reader, new TypeToken<List<Dividend>>(){}.getType());
    }

    private void goToRendimentosPage() {
        //Acessa pagina rendimentos
        if (!findText("rendimentosPage", 3000)) {
            notFound("rendimentosPage");
            return;
        }
        click();
        //Clica pra adicionar rendimento
        if (!find("adicionarRendimentoText", 0.95, 3000)) {
            notFound("adicionarRendimentoText");
            return;
        }
        click();

    }

    private void adicionarRendimentos(Dividend dividend) {
        //Preenche natureza com Outros
        if (!findText("natureza", 230, 3000)) {
            notFound("natureza");
            return;
        }
        clickRelative(136, 52);
        type("Outros");
        enter();

        //Preenche Data recebimento
        tab();
        paste(dividend.getDtActive());

        //Preenche Historico
        if (!findText("historico", 230, 3000)) {
            notFound("historico");
            return;
        }
        clickRelative(125, 51);
        paste("Dividendos da empresa " + dividend.getNameActive(), 500);

        //Preenche Recebido de Exterior
        shiftTab();
        space();

        //Preenche valor
        tab();
        tab();
        paste(dividend.getValue());

        //Incluir Rendimento
        tab();
        enter();

        //Reinvestir
        wait(3000);
        tab();
        enter();

    }

    private void goToPagamentosPage() {
        if (!findText("pagamentosText", 230, 3000)) {
            notFound("pagamentosText");
            return;
        }
        click();
        if (findText("avisoSair", 230, 3000)) {
            click();
        }
        if (!find("adicionarPagamentoText", 0.95, 3000)) {
            notFound("adicionarPagamentoText");
            return;
        }
        click();
    }

    private void adicionarPagamento(Dividend dividend) {
        String natureza = "Imposto pago no exterior";
        String description = "Retenção de Impostos sobre dividendos recebidos da empresa ";

        //Preenche natureza
        if (!findText("natureza", 230, 3000)) {
            notFound("natureza");
            return;
        }
        clickRelative(136, 52);
        type(natureza);
        enter();

        //Preenche Data recebimento
        tab();
        paste(dividend.getDtActive());

        //Preenche Historico
        if (!findText("historico", 230, 3000)) {
            notFound("historico");
            return;
        }
        clickRelative(125, 51);
        paste(description + dividend.getNameActive(), 500);

        //Preenche valor
        tab();
        paste(dividend.getTaxes());

        //Incluir Pagamento
        tab();
        enter();

        //Reinvestir
        wait(3000);
        tab();
        enter();
    }

    private void notFound(String label) {
        System.out.println("Element not found: " + label);
    }

    private void loadResources() throws IOException {
        addImage("avisoSair", "src/resources/avisoSair.png");
        //Rendimentos
        addImage("rendimentosPage", "src/resources/rendimentos/rendimentosPage.png");
        addImage("adicionarRendimento", "src/resources/rendimentos/adicionarRendimento.png");
        addImage("adicionarRendimentoText", "src/resources/rendimentos/adicionarRendimentoText.png");
        addImage("natureza", "src/resources/rendimentos/natureza.png");
        addImage("historico", "src/resources/rendimentos/historico.png");

        //Pagamentos
        addImage("adicionarPagamentoText", "src/resources/pagamentos/adicionarPagamentoText.png");
        addImage("pagamentosText", "src/resources/pagamentos/pagamentosText.png");
    }
}

