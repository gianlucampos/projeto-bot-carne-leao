package br.com.main;

import dev.botcity.framework.bot.DesktopBot;
import dev.botcity.maestro_sdk.BotExecutor;
import dev.botcity.maestro_sdk.runner.BotExecution;
import dev.botcity.maestro_sdk.runner.RunnableAgent;

import java.io.IOException;

public class BotCarneLeao extends DesktopBot implements RunnableAgent {

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

        try {
            goToRendimentosPage();
            adicionarRendimentos();
            adicionarRendimentos();

        } catch (Exception e) {
            e.printStackTrace();
        }
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

    private void adicionarRendimentos() {
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
        paste("01/02/2022");

        //Preenche Historico
        if (!findText("historico", 230, 3000)) {
            notFound("historico");
            return;
        }
        clickRelative(125, 51);
        paste("ABOBORA", 500);

        //Preenche Recebido de Exterior
        shiftTab();
        space();

        //Preenche valor
        tab();
        tab();
        paste("10,00");

        //Incluir Rendimento
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
        addImage("rendimentosPage", "src/resources/rendimentos/rendimentosPage.png");
        addImage("adicionarRendimento", "src/resources/rendimentos/adicionarRendimento.png");
        addImage("adicionarRendimentoText", "src/resources/rendimentos/adicionarRendimentoText.png");
        addImage("natureza", "src/resources/rendimentos/natureza.png");
        addImage("historico", "src/resources/rendimentos/historico.png");
    }
}

