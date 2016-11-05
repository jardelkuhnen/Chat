package br.univel.chat.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainCliente {


	public static void main(String[] args) throws InterruptedException {
		new MainCliente().sendMessage(500);
	}

	public void sendMessage(final Integer quantidade) throws InterruptedException {
		final ExecutorService pool = Executors.newFixedThreadPool(quantidade);
		final List<Callable<Object>> calls = new ArrayList<>(quantidade);
		for (int index = 0; index < quantidade; index++) {
			calls.add(new MessageClient("Message: " + index, "localhost", 1000));
		}
		pool.invokeAll(calls);
		pool.shutdown();
	}
}
