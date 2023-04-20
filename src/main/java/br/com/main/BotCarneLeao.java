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


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void rendimentosPage() {
        if (!find("rendimentosPage", 0.97, 3000)) {
            notFound("rendimentosPage");
            return;
        }
        click();
        if (!find( "adicionarRendimento", 0.95, 5000)) {
            notFound("adicionarRendimento");
            return;
        }
        click();
        if (!findText( "natureza", 230, 3000)) {
            notFound("natureza");
            return;
        }
        clickRelative(136, 52);
        keyUp();
        keyEnter(300);
        tab();
        paste("01/02/2022");
        if (!findText( "historico", 230, 3000)) {
            notFound("historico");
            return;
        }
        clickRelative(125, 51);
        paste("ABOBORA", 500);
        shiftTab();
        space();
        tab();
        tab();
        paste("10,00");
    }

    private void notFound(String label) {
        System.out.println("Element not found: " + label);
    }

    private void loadResources() throws IOException {
        addImage("rendimentosPage", "src/resources/rendimentos/rendimentosPage.png");
        addImage("adicionarRendimento", "src/resources/rendimentos/adicionarRendimento.png");
        addImage("natureza", "src/resources/rendimentos/natureza.png");
        addImage("historico", "src/resources/rendimentos/historico.png");
    }
}

