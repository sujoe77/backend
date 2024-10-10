import json
import requests
import datetime
import hashlib
import hmac
import base64

# Update the customer ID to your Log Analytics workspace ID
customer_id = '63e53a41-bed0-482c-addb-2b707a30d89b'

# For the shared key, use either the primary or the secondary Connected Sources client authentication key   
shared_key = "xxxxxxxx"

# The log type is the name of the event that is being submitted
log_type = 'tracelog_json_CL'

# An example JSON web monitor object
json_data = [{
   "slot_ID": 12345,
    "ID": "5cdad72f-c848-4df0-8aaa-ffe033e75d57",
    "availability_Value": 100,
    "performance_Value": 6.954,
    "measurement_Name": "last_one_hour",
    "duration": 3600,
    "warning_Threshold": 0,
    "critical_Threshold": 0,
    "IsActive": "true"
},
{   
    "slot_ID": 67890,
    "ID": "b6bee458-fb65-492e-996d-61c4d7fbb942",
    "availability_Value": 100,
    "performance_Value": 3.379,
    "measurement_Name": "last_one_hour",
    "duration": 3600,
    "warning_Threshold": 0,
    "critical_Threshold": 0,
    "IsActive": "false"
}]
json_data={"class_name":"com.sxp.logging.core.LogObjectContainer","class_version":"1.1.0","level":"TRACE","timestamp":"2020-04-06T00:00:656Z","user_id":"saxo_payments","application_id":"test_azure_logging_rest","request_id":"503753831:520418641:1586131244655","session_id":"ae9931b4-9168-4b12-be23-7b61e001053f","server":"wls2vm","wls_name":"SWUBS-external","thread_name":"pool-29-thread-1","os_name":"Linux","os_arch":"amd64","os_version":"4.14.35-1902.300.11.el7uek.x86_64","jvm_mode":"mixed mode","jvm_name":"Java(TM) SE Runtime Environment","jvm_version":"1.8.0_171-b11","session_state":"ACTIVE","session_start":"2020-04-06T00:00:655Z","session_touched":"2020-04-06T00:00:655Z","logger":"com.sxp.dao.aspect.SqlLogger","method":"ajc$before$com_sxp_dao_aspect_SqlLogger$1$f1b9b9cc","line":"22","message_id":"","message_text":"SQL: SELECT mdmi.dcn,(select message from dual) message,mdmi.branch FROM FCUBS.MSTB_DLY_MSG_IN mdmi JOIN FCUBS.STTM_BRANCH sttm on mdmi.BRANCH = sttm.BRANCH_CODE WHERE mdmi.SWIFT_MSG_TYPE = '300' AND mdmi.MESSAGE IS NOT NULL AND sttm.END_OF_INPUT ='N' AND mdmi.DCN NOT IN (SELECT IMB_DCN FROM SAXOTRADER.MT300_MAPPING) and mdmi.MAKER_DT_STAMP >=sysdate-14 ORDER BY mdmi.MAKER_DT_STAMP DESC\nCaller: com.sxp.dao.AOracleDAO->executeQuery"};
##json_data={"key":1}
body = json.dumps(json_data)
body = "{\"key\": 2}"
print(body)
#####################
######Functions######  
#####################

# Build the API signature
def build_signature(customer_id, shared_key, date, content_length, method, content_type, resource):
    x_headers = 'x-ms-date:' + date
    string_to_hash = method + "\n" + str(content_length) + "\n" + content_type + "\n" + x_headers + "\n" + resource
    print(string_to_hash)
    bytes_to_hash = string_to_hash.encode()
    decoded_key = base64.b64decode(shared_key)
    encrypted = hmac.new(decoded_key, bytes_to_hash, digestmod=hashlib.sha256).digest();
    print(encrypted.hex())
    encoded_hash = base64.standard_b64encode(encrypted)
    print(encoded_hash.decode())
    authorization = "SharedKey {}:{}".format(customer_id,encoded_hash.decode())
    print(authorization)
    return authorization

# Build and send a request to the POST API
def post_data(customer_id, shared_key, body, log_type):
    method = 'POST'
    content_type = 'application/json'
    resource = '/api/logs'
    rfc1123date = datetime.datetime.utcnow().strftime('%a, %d %b %Y %H:%M:%S GMT')
    #rfc1123date = "Tue, 07 Apr 2020 15:38:34 GMT"
    content_length = len(body)
    signature = build_signature(customer_id, shared_key, rfc1123date, content_length, method, content_type, resource)
    uri = 'https://' + customer_id + '.ods.opinsights.azure.com' + resource + '?api-version=2016-04-01'
    uri = "https://sujoe.free.beeceptor.com"
    print(uri)
    headers = {
        'content-type': content_type,
        'Authorization': signature,
        'Log-Type': log_type,
        'x-ms-date': rfc1123date
    }

    response = requests.post(uri,data=body, headers=headers)
    if (response.status_code >= 200 and response.status_code <= 299):
        print("Response code: {}".format(response.status_code))
        print('Accepted')
    else:
        print("Response code: {}".format(response.status_code))
        print(response.content)

post_data(customer_id, shared_key, body, log_type)