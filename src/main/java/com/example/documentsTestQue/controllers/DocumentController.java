package com.example.documentsTestQue.controllers;

import com.example.documentsTestQue.repositories.DocumentRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.validation.BindingResult;
import com.example.documentsTestQue.models.Document;
import com.example.documentsTestQue.models.DocumentOfProducts;
import com.example.documentsTestQue.models.DocumentOfService;
import com.example.documentsTestQue.services.DocumentService;
import lombok.RequiredArgsConstructor;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller// Этот класс - контроллер
@RequiredArgsConstructor
public class DocumentController {
    private final DocumentService documentService;
    private final DocumentRepository documentRepository;

    @GetMapping("/")
    public String documents(@RequestParam(name = "title", required = false) String title, Model model) {
        // Если параметр 'title' не задан, передаем в модель список всех документов
        // Иначе передаем в модель документы с заданным 'title'
        if (title == null || title.isEmpty()) {
            model.addAttribute("documents", documentService.listAllDocuments());
        } else {
            model.addAttribute("documents", documentService.listDocument(title));
        }
        return "document";
    }

    @GetMapping("/document/search")//метод поиска по id
    public String documentInfoById(@RequestParam(name = "id") Long id, Model model) {
        model.addAttribute("document", documentService.getDocumentById(id));
        return "document-info";
    }

    @GetMapping("/document/createForm")//метод формы создания нового документа
    public String documentCreateForm(@RequestParam(required = false) Long id, Model model, RedirectAttributes redirectAttributes) {
        if (id != null) {
            model.addAttribute("document", documentService.getDocumentById(id));
        }
        model.addAttribute("error", "");
        return "documentCreateForm";
    }

    @GetMapping("/document/{id}")//метод перехода к документу по id
    public String documentInfo(@PathVariable Long id, Model model) {
        model.addAttribute("document", documentService.getDocumentById(id));
        return "document-info";
    }

    @PostMapping("/document/update/{id}")//метод редактирования документа
    public String updateDocument(@PathVariable Long id,
                                 @RequestParam("description") String description,
                                 @RequestParam("author") String author,
                                 @RequestParam("price") Double price) {
        Document documentToUpdate = documentService.getDocumentById(id);
        if (documentToUpdate != null) {
            documentToUpdate.setDescription(description);
            documentToUpdate.setAuthor(author);
            documentToUpdate.setPrice(price);
            documentService.saveDocument(documentToUpdate);
            return "redirect:/document/" + id;
        } else {
            // При разработке был смысл, сейчас не особо, но решил оставить
            return "redirect:/document/" + id;
        }
    }
    @PostMapping("/document/create")//метод обработки создания нового документа
    public String createDocument(@RequestParam("documentType") String documentType,
                                 @RequestParam("title") String title,
                                 @RequestParam("id") Long id,
                                 @RequestParam("description") String description,
                                 @RequestParam("price") Double price,
                                 @RequestParam("author") String author,
                                 RedirectAttributes redirectAttributes) {

        if (id != null && documentService.existsById(id)) { //не успел реализовать вывод ошибки на форму, сделал по другому, решил не удалять
            redirectAttributes.addFlashAttribute("error", "Документ с таким номером уже существует");
            return "redirect:/document/createForm";
        }

        Document document;

        if ("Реализация услуг".equals(documentType)) {
            DocumentOfService documentOfService = new DocumentOfService();
            documentOfService.setId(id);
            documentOfService.setTitle(title);
            documentOfService.setDescription(description);
            documentOfService.setPrice(price);
            documentOfService.setAuthor(author);
            document = documentOfService;
        } else {// определяем тип дочернего документа для абстрактного класса document
            DocumentOfProducts documentOfProducts = new DocumentOfProducts();
            documentOfProducts.setId(id);
            documentOfProducts.setTitle(title);
            documentOfProducts.setDescription(description);
            documentOfProducts.setPrice(price);
            documentOfProducts.setAuthor(author);
            document = documentOfProducts;
        }
        documentService.saveDocument(document);
        return "redirect:/";
    }

    @PostMapping("/document/delete/{id}")//удаление документа (тут мало, там на форме неплохо отработал js)
    public String deleteDocument(@PathVariable Long id) {
        documentService.deleteDocument(id);
        return "redirect:/";
    }
}