package com.archpj.getatestbot.utils;

import com.archpj.getatestbot.components.Buttons;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class OnQuizUpdateHandler {

    public static SendMessage handleUpdate(Update update) {
        if (update.hasMessage()) return handleMessage(update);
        if (update.hasCallbackQuery()) return handleCallbackQuery(update);

        return SendMessage.builder().
                chatId(update.getMyChatMember().getChat().getId()).
                text("""
                        Обработка такого типа сообщений не предусмотрена функционалом.
                        Воспользуйтесь меню.""").
                build();
    }


    private static SendMessage handleMessage(Update update) {
        Message incomingMessage = update.getMessage();
        long employeeId = incomingMessage.getFrom().getId();
        SendMessage message;

        message = SendMessage.builder().
                chatId(employeeId).
                text("""
                            Вы в процессе тестирования.
                            Ответы принимаются только нажатием на одну из кнопок выбора ответа.
                            Если Вы хотите прервать тест, то нажмите кнопку "Отказаться".
                            Примите во внимание что в этом случае результаты не сохранятся и тест нужно будет пройти заново.""").
                replyMarkup(Buttons.rejectTest()).
                build();

        return message;
    }

    private static SendMessage handleCallbackQuery(Update update) {
        CallbackQuery callbackQuery = update.getCallbackQuery();
        long employeeId = callbackQuery.getFrom().getId();
        SendMessage message;

        switch (callbackQuery.getData()) {
            case "Отказаться" -> message = SendMessage.builder().
                    chatId(employeeId).
                    text("reject").
                    build();
            case "A", "B", "C", "D" -> message = SendMessage.builder().
                    chatId(employeeId).
                    text(callbackQuery.getData()).
                    build();
            default -> message = SendMessage.builder().
                        chatId(employeeId).
                        text("Если Вы читаете это сообщение, то что-то пошло не так. Обратитесь к разработчику").
                        build();

        }
        return message;
    }

}