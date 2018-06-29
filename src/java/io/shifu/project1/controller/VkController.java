package io.shifu.project1.controller;

import com.github.scribejava.apis.VkontakteApi;
import com.github.scribejava.apis.vk.VKOAuth2AccessToken;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.*;
import com.github.scribejava.core.oauth.OAuth20Service;
import io.shifu.project1.model.User;
import io.shifu.project1.services.OauthSecurityService;
import io.shifu.project1.services.UserService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.json.JSONObject;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Controller for vk login
 */

@Controller
@PropertySource(value = "classpath:oauth.properties")
public class VkController {

    private final Environment env;

    private static final String NETWORK_NAME = "Vkontakte.ru";
    private static final String PROTECTED_RESOURCE_URL = "https://api.vk.com/method/users.get?v="
            + VkontakteApi.VERSION;

    private final UserService userService;
    private final OauthSecurityService oauthSecurityService;

    @Autowired
    public VkController(UserService userService, OauthSecurityService oauthSecurityService, Environment env) {
        this.userService = userService;
        this.oauthSecurityService = oauthSecurityService;
        this.env = env;
    }


    @RequestMapping(value = "/vkLogin")
    public void vkLogin(HttpServletResponse response) throws IOException {

        final OAuth20Service service = new ServiceBuilder(env.getProperty("vk.clientId"))
                .apiSecret(env.getProperty("vk.clientSecret"))
                .scope("wall,offline,email") // replace with desired scope
                .callback(env.getProperty("vk.callback"))
                .build(VkontakteApi.instance());

        final String authorizationUrl = service.getAuthorizationUrl();
        response.sendRedirect(authorizationUrl);
    }

    @RequestMapping(value = "/callback", method = RequestMethod.GET)
    public String callback(@RequestParam(value = "code", required = false) String code, Model model) throws IOException, ExecutionException, InterruptedException {
        try {

            final OAuth20Service service = new ServiceBuilder(env.getProperty("vk.clientId"))
                    .apiSecret(env.getProperty("vk.clientSecret"))
                    .scope("wall,offline,email") // replace with desired scope
                    .callback(env.getProperty("vk.callback"))
                    .build(VkontakteApi.instance());

            final OAuth2AccessToken accessToken = service.getAccessToken(code);

            final OAuthRequest oauthRequest = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
            service.signRequest(accessToken, oauthRequest);
            final Response response = service.execute(oauthRequest);

            String email = "";
            Long userId = 0l;
            String first_name = "";
            String last_name = "";

            final JSONObject obj = new JSONObject(response.getBody());
            JSONArray array = obj.getJSONArray("response");
            for (int i = 0; i < array.length(); i++) {
                JSONObject temp = array.getJSONObject(i);
                userId = temp.getLong("id");
                first_name = temp.getString("first_name");
                last_name = temp.getString("last_name");
            }


            if (accessToken instanceof VKOAuth2AccessToken) {
                email = ((VKOAuth2AccessToken) accessToken).getEmail();
            }

            //todo check email
            User user = userService.findByVkId(userId);

            if (user == null) {
                user = new User();
                user.setVkId(userId);
                user.setUsername(first_name + " " + last_name);
                user.setPassword(null);
                user.setEmail(email);
                user.setEnabled(true);
                userService.saveVk(user);
            }

            oauthSecurityService.vkLogin(user);
        } catch (OAuth2AccessTokenErrorResponse e) {
            model.addAttribute("error", "Oops!  Error login, try again later pls.");
            return "login";
        }

        return "redirect:/welcome";
    }
}