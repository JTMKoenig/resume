/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.contactlistspringmvc.controller;

import com.sg.contactlistspringmvc.dao.ContactListDao;
import com.sg.contactlistspringmvc.model.Contact;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author jono
 */
@Controller
public class ContactController {

    ContactListDao dao;

    @Inject
    public ContactController(ContactListDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/displayContactsPage", method = RequestMethod.GET)
    public String displayContactsPage(Model model) {
        if (!model.containsAttribute("contact")) {
            model.addAttribute("contact", new Contact());
        }

        List<Contact> contactList = dao.getAllContacts();

        model.addAttribute("contactList", contactList);

        return "contacts";
    }

    @RequestMapping(value = "/addContact", method = RequestMethod.POST)
    public String addContact(@Valid @ModelAttribute Contact contact, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("contact", contact);
            return "contacts";
        }

        dao.addContact(contact);

        return "redirect:displayContactsPage";
    }

    @RequestMapping(value = "/displayContactDetails", method = RequestMethod.GET)
    public String displayContactDetails(HttpServletRequest request, Model model) {

        String contactIdParameter = request.getParameter("contactId");
        Long contactId = Long.parseLong(contactIdParameter);

        Contact contact = dao.getContactById(contactId);

        model.addAttribute("contact", contact);

        return "contactDetails";
    }

    @RequestMapping(value = "/deleteContact", method = RequestMethod.GET)
    public String deleteContact(HttpServletRequest request) {

        String contactIdParameter = request.getParameter("contactId");
        Long contactId = Long.parseLong(contactIdParameter);

        dao.removeContact(contactId);

        return "redirect:displayContactsPage";
    }

    @RequestMapping(value = "/displayEditContactForm", method = RequestMethod.GET)
    public String displayEditContactForm(HttpServletRequest request, Model model) {

        String contactIdParameter = request.getParameter("contactId");
        Long contactId = Long.parseLong(contactIdParameter);

        Contact contact = dao.getContactById(contactId);
        model.addAttribute("contact", contact);

        return "editContactForm";
    }

    @RequestMapping(value = "/editContact", method = RequestMethod.POST)
    public String editContact(@Valid @ModelAttribute("contact") Contact contact, BindingResult result) {

        if (result.hasErrors()) {
            return "editContactForm";
        }

        dao.updateContact(contact);
        return "redirect:displayContactsPage";
    }

}
