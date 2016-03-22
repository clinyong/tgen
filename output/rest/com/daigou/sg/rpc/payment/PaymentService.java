package com.daigou.sg.rpc.payment;

/**
 * This file is auto-generated by tgen
 * Don't change manually
 */

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.daigou.selfstation.utils.JsonUtils;
import com.daigou.selfstation.utils.NetworkError;
import com.daigou.selfstation.utils.RpcRequest;
import com.daigou.selfstation.system.AppUrl;
import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.Gson;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;

public class PaymentService {
    private static final Gson gson = new Gson();
    private static int msgID = 1;
    private static RequestQueue queue;

    private PaymentService() {
        // Constructor hidden because this is a singleton
    }

    public static void init(RequestQueue requestQueue) {
        queue = requestQueue;
    }

    private static String getMsgID() {
        msgID += 1;
        return Integer.toString(msgID);
    }

    public static void AddWithdrawReqeust(final String bankName, final String account, final double amount, final String reason, final Listener<String> listener) {
        RpcRequest req = new RpcRequest(Request.Method.POST, AppUrl.kJsonRpcCoreUrl + "Payment/AddWithdrawReqeust",
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        String result;
                        JsonParser jp = JsonUtils.getJsonRpcResult(response);
                        
                        jp.nextToken();
                        result = jp.getText();

                        jp.close();

                        listener.onResponse(result);
                    } catch (IOException ex) {
                        Log.d("ex", ex.toString());
                        Log.d("jsonObject", response);
                        listener.onResponse(null);
                    }
                }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.getCause() != null && error.getCause() instanceof java.net.UnknownHostException) {
                    NetworkError.unKnowHost();
                } else if (error.networkResponse != null) {
                    NetworkError.networkError(error.networkResponse.statusCode);
                } else {
                    listener.onResponse(null);
                }
            }
        }) {
            @Override
            public byte[] getBody() {
                HashMap<String, Object> msg = new HashMap<String, Object>();
                msg.put("bankName", bankName);
                msg.put("account", account);
                msg.put("amount", amount);
                msg.put("reason", reason);

                return gson.toJson(msg).getBytes(Charset.forName("UTF-8"));
            }
        };
        queue.add(req);
    }

    public static void ConfirmPayments(final ArrayList<Integer> paymentIds, final Listener<Double> listener) {
        RpcRequest req = new RpcRequest(Request.Method.POST, AppUrl.kJsonRpcCoreUrl + "Payment/ConfirmPayments",
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        Double result;
                        JsonParser jp = JsonUtils.getJsonRpcResult(response);
                        
                        result = Double.fromJSON(jp);

                        jp.close();

                        listener.onResponse(result);
                    } catch (IOException ex) {
                        Log.d("ex", ex.toString());
                        Log.d("jsonObject", response);
                        listener.onResponse(null);
                    }
                }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.getCause() != null && error.getCause() instanceof java.net.UnknownHostException) {
                    NetworkError.unKnowHost();
                } else if (error.networkResponse != null) {
                    NetworkError.networkError(error.networkResponse.statusCode);
                } else {
                    listener.onResponse(null);
                }
            }
        }) {
            @Override
            public byte[] getBody() {
                HashMap<String, Object> msg = new HashMap<String, Object>();
                msg.put("paymentIds", paymentIds);

                return gson.toJson(msg).getBytes(Charset.forName("UTF-8"));
            }
        };
        queue.add(req);
    }

    public static void GetCreditCardFee(final Listener<TCreditCardInfo> listener) {
        RpcRequest req = new RpcRequest(Request.Method.POST, AppUrl.kJsonRpcCoreUrl + "Payment/GetCreditCardFee",
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        TCreditCardInfo result;
                        JsonParser jp = JsonUtils.getJsonRpcResult(response);
                        
                        result = TCreditCardInfo.fromJSON(jp);

                        jp.close();

                        listener.onResponse(result);
                    } catch (IOException ex) {
                        Log.d("ex", ex.toString());
                        Log.d("jsonObject", response);
                        listener.onResponse(null);
                    }
                }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.getCause() != null && error.getCause() instanceof java.net.UnknownHostException) {
                    NetworkError.unKnowHost();
                } else if (error.networkResponse != null) {
                    NetworkError.networkError(error.networkResponse.statusCode);
                } else {
                    listener.onResponse(null);
                }
            }
        }) {
            @Override
            public byte[] getBody() {
                return "".getBytes(Charset.forName("UTF-8"));
            }
        };
        queue.add(req);
    }

    public static void GetPaymentDetail(final int paymentId, final Listener<TPaymentBillDetail> listener) {
        RpcRequest req = new RpcRequest(Request.Method.POST, AppUrl.kJsonRpcCoreUrl + "Payment/GetPaymentDetail",
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        TPaymentBillDetail result;
                        JsonParser jp = JsonUtils.getJsonRpcResult(response);
                        
                        result = TPaymentBillDetail.fromJSON(jp);

                        jp.close();

                        listener.onResponse(result);
                    } catch (IOException ex) {
                        Log.d("ex", ex.toString());
                        Log.d("jsonObject", response);
                        listener.onResponse(null);
                    }
                }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.getCause() != null && error.getCause() instanceof java.net.UnknownHostException) {
                    NetworkError.unKnowHost();
                } else if (error.networkResponse != null) {
                    NetworkError.networkError(error.networkResponse.statusCode);
                } else {
                    listener.onResponse(null);
                }
            }
        }) {
            @Override
            public byte[] getBody() {
                HashMap<String, Object> msg = new HashMap<String, Object>();
                msg.put("paymentId", paymentId);

                return gson.toJson(msg).getBytes(Charset.forName("UTF-8"));
            }
        };
        queue.add(req);
    }

    public static void GetPaymentListByStatus(final String status, final int offset, final int limit, final Listener<ArrayList<TPaymentBillSummary>> listener) {
        RpcRequest req = new RpcRequest(Request.Method.POST, AppUrl.kJsonRpcCoreUrl + "Payment/GetPaymentListByStatus",
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        ArrayList<TPaymentBillSummary> result;
                        JsonParser jp = JsonUtils.getJsonRpcResult(response);
                        
                        result = TPaymentBillSummary.fromJSONArray(jp);

                        jp.close();

                        listener.onResponse(result);
                    } catch (IOException ex) {
                        Log.d("ex", ex.toString());
                        Log.d("jsonObject", response);
                        listener.onResponse(null);
                    }
                }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.getCause() != null && error.getCause() instanceof java.net.UnknownHostException) {
                    NetworkError.unKnowHost();
                } else if (error.networkResponse != null) {
                    NetworkError.networkError(error.networkResponse.statusCode);
                } else {
                    listener.onResponse(null);
                }
            }
        }) {
            @Override
            public byte[] getBody() {
                HashMap<String, Object> msg = new HashMap<String, Object>();
                msg.put("status", status);
                msg.put("offset", offset);
                msg.put("limit", limit);

                return gson.toJson(msg).getBytes(Charset.forName("UTF-8"));
            }
        };
        queue.add(req);
    }

    public static void GetPaymentSummary(final Listener<TPaymentSummary> listener) {
        RpcRequest req = new RpcRequest(Request.Method.POST, AppUrl.kJsonRpcCoreUrl + "Payment/GetPaymentSummary",
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        TPaymentSummary result;
                        JsonParser jp = JsonUtils.getJsonRpcResult(response);
                        
                        result = TPaymentSummary.fromJSON(jp);

                        jp.close();

                        listener.onResponse(result);
                    } catch (IOException ex) {
                        Log.d("ex", ex.toString());
                        Log.d("jsonObject", response);
                        listener.onResponse(null);
                    }
                }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.getCause() != null && error.getCause() instanceof java.net.UnknownHostException) {
                    NetworkError.unKnowHost();
                } else if (error.networkResponse != null) {
                    NetworkError.networkError(error.networkResponse.statusCode);
                } else {
                    listener.onResponse(null);
                }
            }
        }) {
            @Override
            public byte[] getBody() {
                return "".getBytes(Charset.forName("UTF-8"));
            }
        };
        queue.add(req);
    }

    public static void GetPrepayBalance(final Listener<Double> listener) {
        RpcRequest req = new RpcRequest(Request.Method.POST, AppUrl.kJsonRpcCoreUrl + "Payment/GetPrepayBalance",
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        Double result;
                        JsonParser jp = JsonUtils.getJsonRpcResult(response);
                        
                        result = Double.fromJSON(jp);

                        jp.close();

                        listener.onResponse(result);
                    } catch (IOException ex) {
                        Log.d("ex", ex.toString());
                        Log.d("jsonObject", response);
                        listener.onResponse(null);
                    }
                }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.getCause() != null && error.getCause() instanceof java.net.UnknownHostException) {
                    NetworkError.unKnowHost();
                } else if (error.networkResponse != null) {
                    NetworkError.networkError(error.networkResponse.statusCode);
                } else {
                    listener.onResponse(null);
                }
            }
        }) {
            @Override
            public byte[] getBody() {
                return "".getBytes(Charset.forName("UTF-8"));
            }
        };
        queue.add(req);
    }

    public static void GetTopUpDescription(final Listener<ArrayList<String>> listener) {
        RpcRequest req = new RpcRequest(Request.Method.POST, AppUrl.kJsonRpcCoreUrl + "Payment/GetTopUpDescription",
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        ArrayList<String> result;
                        JsonParser jp = JsonUtils.getJsonRpcResult(response);
                        
                        result = JsonUtils.readStringList(jp);

                        jp.close();

                        listener.onResponse(result);
                    } catch (IOException ex) {
                        Log.d("ex", ex.toString());
                        Log.d("jsonObject", response);
                        listener.onResponse(null);
                    }
                }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.getCause() != null && error.getCause() instanceof java.net.UnknownHostException) {
                    NetworkError.unKnowHost();
                } else if (error.networkResponse != null) {
                    NetworkError.networkError(error.networkResponse.statusCode);
                } else {
                    listener.onResponse(null);
                }
            }
        }) {
            @Override
            public byte[] getBody() {
                return "".getBytes(Charset.forName("UTF-8"));
            }
        };
        queue.add(req);
    }

    public static void GetWithdrawBanks(final Listener<ArrayList<String>> listener) {
        RpcRequest req = new RpcRequest(Request.Method.POST, AppUrl.kJsonRpcCoreUrl + "Payment/GetWithdrawBanks",
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        ArrayList<String> result;
                        JsonParser jp = JsonUtils.getJsonRpcResult(response);
                        
                        result = JsonUtils.readStringList(jp);

                        jp.close();

                        listener.onResponse(result);
                    } catch (IOException ex) {
                        Log.d("ex", ex.toString());
                        Log.d("jsonObject", response);
                        listener.onResponse(null);
                    }
                }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.getCause() != null && error.getCause() instanceof java.net.UnknownHostException) {
                    NetworkError.unKnowHost();
                } else if (error.networkResponse != null) {
                    NetworkError.networkError(error.networkResponse.statusCode);
                } else {
                    listener.onResponse(null);
                }
            }
        }) {
            @Override
            public byte[] getBody() {
                return "".getBytes(Charset.forName("UTF-8"));
            }
        };
        queue.add(req);
    }

    public static void TopUp(final String transactionNumber, final String bankName, final String telephone, final double amount, final String paymentMethod, final ArrayList<Integer> paymentIds, final String payDate, final Listener<String> listener) {
        RpcRequest req = new RpcRequest(Request.Method.POST, AppUrl.kJsonRpcCoreUrl + "Payment/TopUp",
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        String result;
                        JsonParser jp = JsonUtils.getJsonRpcResult(response);
                        
                        jp.nextToken();
                        result = jp.getText();

                        jp.close();

                        listener.onResponse(result);
                    } catch (IOException ex) {
                        Log.d("ex", ex.toString());
                        Log.d("jsonObject", response);
                        listener.onResponse(null);
                    }
                }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.getCause() != null && error.getCause() instanceof java.net.UnknownHostException) {
                    NetworkError.unKnowHost();
                } else if (error.networkResponse != null) {
                    NetworkError.networkError(error.networkResponse.statusCode);
                } else {
                    listener.onResponse(null);
                }
            }
        }) {
            @Override
            public byte[] getBody() {
                HashMap<String, Object> msg = new HashMap<String, Object>();
                msg.put("transactionNumber", transactionNumber);
                msg.put("bankName", bankName);
                msg.put("telephone", telephone);
                msg.put("amount", amount);
                msg.put("paymentMethod", paymentMethod);
                msg.put("paymentIds", paymentIds);
                msg.put("payDate", payDate);

                return gson.toJson(msg).getBytes(Charset.forName("UTF-8"));
            }
        };
        queue.add(req);
    }

    public static void UserDoCreditCardTopUp(final double total, final double creditCardFee, final ArrayList<String> paymentIds, final String telephone, final Listener<String> listener) {
        RpcRequest req = new RpcRequest(Request.Method.POST, AppUrl.kJsonRpcCoreUrl + "Payment/UserDoCreditCardTopUp",
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        String result;
                        JsonParser jp = JsonUtils.getJsonRpcResult(response);
                        
                        jp.nextToken();
                        result = jp.getText();

                        jp.close();

                        listener.onResponse(result);
                    } catch (IOException ex) {
                        Log.d("ex", ex.toString());
                        Log.d("jsonObject", response);
                        listener.onResponse(null);
                    }
                }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.getCause() != null && error.getCause() instanceof java.net.UnknownHostException) {
                    NetworkError.unKnowHost();
                } else if (error.networkResponse != null) {
                    NetworkError.networkError(error.networkResponse.statusCode);
                } else {
                    listener.onResponse(null);
                }
            }
        }) {
            @Override
            public byte[] getBody() {
                HashMap<String, Object> msg = new HashMap<String, Object>();
                msg.put("total", total);
                msg.put("creditCardFee", creditCardFee);
                msg.put("paymentIds", paymentIds);
                msg.put("telephone", telephone);

                return gson.toJson(msg).getBytes(Charset.forName("UTF-8"));
            }
        };
        queue.add(req);
    }

    public static void UserGetPrimePaymentSummary(final Listener<TPrimePaymentSummary> listener) {
        RpcRequest req = new RpcRequest(Request.Method.POST, AppUrl.kJsonRpcCoreUrl + "Payment/UserGetPrimePaymentSummary",
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        TPrimePaymentSummary result;
                        JsonParser jp = JsonUtils.getJsonRpcResult(response);
                        
                        result = TPrimePaymentSummary.fromJSON(jp);

                        jp.close();

                        listener.onResponse(result);
                    } catch (IOException ex) {
                        Log.d("ex", ex.toString());
                        Log.d("jsonObject", response);
                        listener.onResponse(null);
                    }
                }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.getCause() != null && error.getCause() instanceof java.net.UnknownHostException) {
                    NetworkError.unKnowHost();
                } else if (error.networkResponse != null) {
                    NetworkError.networkError(error.networkResponse.statusCode);
                } else {
                    listener.onResponse(null);
                }
            }
        }) {
            @Override
            public byte[] getBody() {
                return "".getBytes(Charset.forName("UTF-8"));
            }
        };
        queue.add(req);
    }

    public static void UserPayParcelPayment(final ArrayList<Integer> paymentBillIds, final Listener<TPayParcelPaymentResult> listener) {
        RpcRequest req = new RpcRequest(Request.Method.POST, AppUrl.kJsonRpcCoreUrl + "Payment/UserPayParcelPayment",
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        TPayParcelPaymentResult result;
                        JsonParser jp = JsonUtils.getJsonRpcResult(response);
                        
                        result = TPayParcelPaymentResult.fromJSON(jp);

                        jp.close();

                        listener.onResponse(result);
                    } catch (IOException ex) {
                        Log.d("ex", ex.toString());
                        Log.d("jsonObject", response);
                        listener.onResponse(null);
                    }
                }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.getCause() != null && error.getCause() instanceof java.net.UnknownHostException) {
                    NetworkError.unKnowHost();
                } else if (error.networkResponse != null) {
                    NetworkError.networkError(error.networkResponse.statusCode);
                } else {
                    listener.onResponse(null);
                }
            }
        }) {
            @Override
            public byte[] getBody() {
                HashMap<String, Object> msg = new HashMap<String, Object>();
                msg.put("paymentBillIds", paymentBillIds);

                return gson.toJson(msg).getBytes(Charset.forName("UTF-8"));
            }
        };
        queue.add(req);
    }

    public static void UserPayPrimePayment(final String primeType, final Listener<TPrimePaymentResult> listener) {
        RpcRequest req = new RpcRequest(Request.Method.POST, AppUrl.kJsonRpcCoreUrl + "Payment/UserPayPrimePayment",
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        TPrimePaymentResult result;
                        JsonParser jp = JsonUtils.getJsonRpcResult(response);
                        
                        result = TPrimePaymentResult.fromJSON(jp);

                        jp.close();

                        listener.onResponse(result);
                    } catch (IOException ex) {
                        Log.d("ex", ex.toString());
                        Log.d("jsonObject", response);
                        listener.onResponse(null);
                    }
                }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.getCause() != null && error.getCause() instanceof java.net.UnknownHostException) {
                    NetworkError.unKnowHost();
                } else if (error.networkResponse != null) {
                    NetworkError.networkError(error.networkResponse.statusCode);
                } else {
                    listener.onResponse(null);
                }
            }
        }) {
            @Override
            public byte[] getBody() {
                HashMap<String, Object> msg = new HashMap<String, Object>();
                msg.put("primeType", primeType);

                return gson.toJson(msg).getBytes(Charset.forName("UTF-8"));
            }
        };
        queue.add(req);
    }

    public static void UserRenewPrime(final Listener<TPrimePaymentResult> listener) {
        RpcRequest req = new RpcRequest(Request.Method.POST, AppUrl.kJsonRpcCoreUrl + "Payment/UserRenewPrime",
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        TPrimePaymentResult result;
                        JsonParser jp = JsonUtils.getJsonRpcResult(response);
                        
                        result = TPrimePaymentResult.fromJSON(jp);

                        jp.close();

                        listener.onResponse(result);
                    } catch (IOException ex) {
                        Log.d("ex", ex.toString());
                        Log.d("jsonObject", response);
                        listener.onResponse(null);
                    }
                }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.getCause() != null && error.getCause() instanceof java.net.UnknownHostException) {
                    NetworkError.unKnowHost();
                } else if (error.networkResponse != null) {
                    NetworkError.networkError(error.networkResponse.statusCode);
                } else {
                    listener.onResponse(null);
                }
            }
        }) {
            @Override
            public byte[] getBody() {
                return "".getBytes(Charset.forName("UTF-8"));
            }
        };
        queue.add(req);
    }
}
