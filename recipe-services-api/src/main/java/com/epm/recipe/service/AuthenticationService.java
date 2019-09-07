package com.epm.recipe.service;

import com.epm.recipe.domain.user.AuthenticationToken;
import com.epm.recipe.domain.user.UserIdentity;

public interface AuthenticationService {

    AuthenticationToken generateToken(Long id);

    UserIdentity resolveUserId(AuthenticationToken token);
}
