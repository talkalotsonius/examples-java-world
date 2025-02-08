/* global karate, Java */
/* jshint esversion: 6 */

// `fn` function expected by Karate, see https://github.com/karatelabs/karate#karate-configjs
// noinspection JSUnusedGlobalSymbols,FunctionNamingConventionJS
function fn() {
    'use strict';

    if (baseConfigurationError === 'exit(1)') {
        karate.log("A fatal base configuration error occurred");
        return { configurationError: 'exit(1)' };
    }

    let basePath = "/v1/mypoint/"; // Common base path
    let resource = {
        value: {
            endpoint: `${basePath}value`,
            optionalPathAmendments: '',
            allowedmethodswithpayload: ['POST'],
            allowedmethodswithoutpayload: ['GET'],
            notallowedmethods: [],
            requesttype: 'application/json',
            responsetype: 'application/json',
            validpayload: 'payloads/validRequest.json',
            invalidpayload: {
                badrequest: 'payloads/invalidRequest.json',
                malformedrequest: 'payloads/malformedRequest.txt',
            },
        },
    };
    resource = calculateNotAllowedHttpMethods(resource);

    return {
        resource: resource,
    };
}
