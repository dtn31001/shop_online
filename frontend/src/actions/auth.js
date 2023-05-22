import authService from "../services/auth.service";
import {
  REGISTER_SUCCESS,
  SET_MESSAGE,
  REGISTER_FAIL,
  LOGIN_SUCCESS,
  LOGIN_FAIL,
  LOGOUT,
} from "./types";

export const register =
  (username, email, password, phone, adress) => (dispatch) => {
    return authService.register(username, email, password, phone, adress).then(
      (response) => {
        dispatch({
          type: REGISTER_SUCCESS,
        });
        dispatch({
          type: SET_MESSAGE,
          payload: response.data.message,
        });

        return Promise.resolve();
      },

      (error) => {
        const message = error.toString();
        dispatch({
          type: REGISTER_FAIL,
        });
        dispatch({
          type: SET_MESSAGE,
          payload: message,
        });
        return Promise.reject();
      }
    );
  };

export const login = (username, password) => (dispatch) => {
  return authService.login(username, password).then(
    (data) => {
      dispatch({
        type: LOGIN_SUCCESS,
        payload: { user: data },
      });
      return Promise.resolve();
    },

    (error) => {
      const message = error.toString();
      dispatch({
        type: LOGIN_FAIL,
      });
      dispatch({
        type: SET_MESSAGE,
        payload: message,
      });
      return Promise.reject();
    }
  );
};

export const logout = () => (dispatch) => {
  authService.logout();
  dispatch({
    type: LOGOUT,
  });
};
