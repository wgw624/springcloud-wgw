window.__require = function e(t, n, r) {
  function s(o, u) {
    if (!n[o]) {
      if (!t[o]) {
        var b = o.split("/");
        b = b[b.length - 1];
        if (!t[b]) {
          var a = "function" == typeof __require && __require;
          if (!u && a) return a(b, !0);
          if (i) return i(b, !0);
          throw new Error("Cannot find module '" + o + "'");
        }
        o = b;
      }
      var f = n[o] = {
        exports: {}
      };
      t[o][0].call(f.exports, function(e) {
        var n = t[o][1][e];
        return s(n || e);
      }, f, f.exports, e, t, n, r);
    }
    return n[o].exports;
  }
  var i = "function" == typeof __require && __require;
  for (var o = 0; o < r.length; o++) s(r[o]);
  return s;
}({
  BaseUI: [ function(require, module, exports) {
    "use strict";
    cc._RF.push(module, "fc81635hptBX7G9WJngixRH", "BaseUI");
    "use strict";
    var BaseUI = cc.Class({
      extends: cc.Component,
      statics: {
        className: "CMBaseUI",
        getUrl: function getUrl() {}
      },
      properties: {
        mTag: {
          default: "",
          tooltip: "tag\u6807\u8bb0"
        },
        mUrl: {
          default: "",
          tooltip: "ui\u8d44\u6e90\u8def\u5f84\u540d"
        }
      },
      start: function start() {},
      onShow: function onShow() {},
      onHide: function onHide() {}
    });
    module.exports = BaseUI;
    cc._RF.pop();
  }, {} ],
  CMAudioManager: [ function(require, module, exports) {
    "use strict";
    cc._RF.push(module, "12bffDOUAxM3Zu5d3t3xW3P", "CMAudioManager");
    "use strict";
    var _CMAudioManager = cc.Class({
      statics: {
        _instance: null,
        getInstance: function getInstance() {
          null == _CMAudioManager._instance && (_CMAudioManager._instance = new _CMAudioManager());
          return _CMAudioManager._instance;
        }
      },
      properties: {
        audioDir: "",
        isCloseSound: false,
        backgoundMusicName: ""
      },
      playSound: function playSound(soundName, isLoop, volume) {
        if (this.isCloseSound) return;
        var path = this.audioDir + soundName;
        cc.loader.loadRes(path, cc.AudioClip, function(err, clip) {
          if (err) {
            cc.error(err);
            return;
          }
          var audioID = cc.audioEngine.play(clip, loop || false, volume || 1);
        });
      },
      stopAll: function stopAll() {
        cc.audioEngine.stopAll();
      },
      pauseAll: function pauseAll() {
        cc.audioEngine.pauseAll();
      },
      resumeAll: function resumeAll() {
        cc.audioEngine.resumeAll();
      },
      playBackgroundMusic: function playBackgroundMusic(backgoundMusicName) {
        if (this.backgoundMusicName == backgoundMusicName) return;
        if (this.isCloseSound) return;
        cc.audioEngine.stopMusic();
        var path = this.audioDir + soundName;
        cc.loader.loadRes(path, cc.AudioClip, function(err, clip) {
          if (err) {
            cc.error(err);
            return;
          }
          cc.audioEngine.playMusic(clip, true);
        });
      },
      resumeBackgroundMusic: function resumeBackgroundMusic() {
        cc.audioEngine.stopMusic();
        var path = this.audioDir + this.backgoundMusicName;
        cc.loader.loadRes(path, cc.AudioClip, function(err, clip) {
          if (err) {
            cc.error(err);
            return;
          }
          cc.audioEngine.playMusic(clip, true);
        });
      },
      pauseBackgroundMusic: function pauseBackgroundMusic() {
        cc.audioEngine.pauseMusic();
      },
      stopBackgroundMusic: function stopBackgroundMusic() {
        cc.audioEngine.stopMusic();
      },
      setBackgroundMusicVolume: function setBackgroundMusicVolume(volume) {
        volume = Math.max(0, Math.min(1, volume));
        cc.audioEngine.setMusicVolume(volume);
      },
      isMusicPlaying: function isMusicPlaying() {
        cc.audioEngine.isMusicPlaying();
      }
    });
    module.exports = _CMAudioManager;
    cc._RF.pop();
  }, {} ],
  CMBaseUI: [ function(require, module, exports) {
    "use strict";
    cc._RF.push(module, "a3e76nZ05VMjIEsh0t0oMBe", "CMBaseUI");
    "use strict";
    var CMConfig = require("../CMConfig");
    var CMBaseUI = cc.Class({
      extends: cc.Component,
      statics: {
        className: "CMBaseUI",
        getUrl: function getUrl() {
          return CMConfig.PREFAB_DIR + this.className;
        }
      },
      properties: {},
      start: function start() {}
    });
    module.exports = CMBaseUI;
    cc._RF.pop();
  }, {
    "../CMConfig": "CMConfig"
  } ],
  CMConfig: [ function(require, module, exports) {
    "use strict";
    cc._RF.push(module, "b1d43dP2ONMeriHouKUzKdg", "CMConfig");
    "use strict";
    var CMConfig = {
      VERSION: "1.0.0",
      DEBUG: true,
      PREFAB_DIR: "prefab/ui/",
      AUDIO_DIR: "audio",
      SCREEN_WIDTH: 1334,
      SCREEN_HEIGHT: 750,
      MAX_Z_ORDER: 100
    };
    module.exports = CMConfig;
    cc._RF.pop();
  }, {} ],
  CMDataManager: [ function(require, module, exports) {
    "use strict";
    cc._RF.push(module, "fcf946r4g1IyaCKvFRJ0dAg", "CMDataManager");
    "use strict";
    cc.Class({
      extends: cc.Component,
      properties: {},
      statics: {}
    });
    cc._RF.pop();
  }, {} ],
  CMEventMgr: [ function(require, module, exports) {
    "use strict";
    cc._RF.push(module, "8933102FpRA/ZB0YWRzl9qx", "CMEventMgr");
    "use strict";
    var LOCAL_CONST = {
      PROCESSING_TIME: 300
    };
    var EventTypeEnum = cc.Enum({
      SCREEN_BLOCK: "BLOCK_SCREEN",
      SCREEN_DEBLOCK: "DEBLOCK_SCREEN"
    });
    var _CMEventMgr = {
      _change: 0,
      _evtTypeIdArray: [],
      _evtObjArray: [],
      _nextEvtObjArray: [],
      _listenerNextId: -1,
      on: function on(evtType, evtFunc, evtPriority, evtOnce, evtTarget) {
        evtPriority = evtPriority || 5;
        evtOnce = evtOnce || false;
        this._listenerNextId += 1;
        this._evtTypeIdArray = this._evtTypeIdArray || [];
        this._evtTypeIdArray[evtType] = this._evtTypeIdArray[evtType] || [];
        this._evtTypeIdArray[evtType][evtPriority] = this._evtTypeIdArray[evtType][evtPriority] || {};
        this._evtTypeIdArray[evtType][evtPriority][this._listenerNextId.toString()] = this._listenerNextId;
        this._evtObjArray[this._listenerNextId.toString()] = {
          type: evtType,
          handler: evtFunc,
          once: evtOnce,
          target: evtTarget,
          priority: evtPriority
        };
        return this._listenerNextId;
      },
      once: function once(evtType, evtFunc, evtPriority, evtObj) {
        return this.on(evtType, evtFunc, evtPriority, true, evtObj);
      },
      off: function off(listenerNextId) {
        var tEvent = this._evtObjArray[listenerNextId];
        if (!tEvent) return;
        var tEvtType = tEvent.type;
        var tEvtPriority = tEvent.priority;
        delete this._evtTypeIdArray[tEvtType][tEvtPriority][listenerNextId.toString()];
        delete this._evtObjArray[listenerNextId.toString()];
      },
      offFromTarget: function offFromTarget(evtTarget) {
        if (!evtTarget) return;
        var tEvtObj;
        for (var key in this._evtObjArray) {
          tEvtObj = this._evtObjArray[key];
          if (tEvtObj.target == evtTarget) {
            delete this._evtTypeIdArray[evtType][evtPriority][key];
            delete this._evtObjArray[key];
          }
        }
      },
      emit: function emit(event) {
        var tPriorityArray = this._evtTypeIdArray[event.type];
        if (!tPriorityArray) {
          cc.log("\u4e8b\u4ef6:" + event.type + "\u6ca1\u6709\u6ce8\u518c");
          return;
        }
        event._stop = false;
        event.stop = function() {
          event._stop = true;
        };
        for (var priorityIdx in tPriorityArray) {
          var tIdMap = tPriorityArray[priorityIdx];
          if (!tIdMap) continue;
          for (var idx in tIdMap) {
            var tEvtObj = this._evtObjArray[idx];
            tEvtObj.handler(event);
            tEvtObj.once && this.off(idx);
            if (tEvtObj._stop) {
              cc.log("%s [Event] dispatchEvent() - break dispatching for event %s", tEvtObj.target, tEvtObj.type);
              break;
            }
          }
        }
      },
      nextFrameEmit: function nextFrameEmit(event) {
        this._nextEvtObjArray.push(event);
      },
      update: function update() {
        if (this._nextEvtObjArray.length > 0) {
          var tCurrentTime = Date.now();
          var tNextEvent;
          var diffInterval;
          while (this._nextEvtObjArray.length > 0) {
            tNextEvent = this._nextEvtObjArray.shift();
            this.emit(tNextEvent);
            diffInterval = Date.now() - tCurrentTime;
            if (diffInterval > LOCAL_CONST.PROCESSING_TIME) break;
          }
        }
      },
      clearAll: function clearAll() {
        this._nextEvtObjArray.length = 0;
        this._evtObjArray.length = 0;
        this._evtTypeIdArray.length = 0;
        this._listenerNextId = -1;
      }
    };
    module.exports = {
      CMEventMgr: _CMEventMgr,
      EventTypeEnum: EventTypeEnum
    };
    cc._RF.pop();
  }, {} ],
  CMFormulaForComputing: [ function(require, module, exports) {
    "use strict";
    cc._RF.push(module, "4daafMfyRFN4IBZOZHO3gO7", "CMFormulaForComputing");
    "use strict";
    var Computing = function() {
      function Computing() {}
      var _proto = Computing.prototype;
      _proto.add = function add(a, b) {
        var a1 = parseInt(a);
        var b1 = parseInt(b);
        if (isNaN(a1) || isNaN(b1)) {
          console.log("\u8f93\u5165\u7684\u7c7b\u578b\u4e0d\u662f\u6570\u5b57");
          return "";
        }
        var c = a1 + b1;
        return String(c);
      };
      _proto.sub = function sub(a, b) {
        var a1 = parseInt(a);
        var b1 = parseInt(b);
        if (isNaN(a1) || isNaN(b1)) {
          console.log("\u8f93\u5165\u7684\u7c7b\u578b\u4e0d\u662f\u6570\u5b57");
          return "";
        }
        var c = a1 - b1;
        return String(c);
      };
      _proto.mul = function mul(a, b) {
        var a1 = parseInt(a);
        var b1 = parseInt(b);
        if (isNaN(a1) || isNaN(b1)) {
          console.log("\u8f93\u5165\u7684\u7c7b\u578b\u4e0d\u662f\u6570\u5b57");
          return "";
        }
        var c = a1 * b1;
        return String(c);
      };
      _proto.div = function div(a, b) {
        var a1 = parseInt(a);
        var b1 = parseInt(b);
        if (isNaN(a1) || isNaN(b1)) {
          console.log("\u8f93\u5165\u7684\u7c7b\u578b\u4e0d\u662f\u6570\u5b57");
          return "";
        }
        if (0 == b1) return "\u9664\u6570\u4e0d\u80fd\u4e3a0";
        var c = a1 / b1;
        return String(c);
      };
      _proto.additionSubtraction = function additionSubtraction(formula) {
        if ("string" != typeof formula) {
          console.log("\u53c2\u6570\u5fc5\u987b\u662f\u5b57\u7b26\u4e32\uff01\uff01");
          return "\u53c2\u6570\u5fc5\u987b\u662f\u5b57\u7b26\u4e32\uff01\uff01";
        }
        var realFormula = "";
        var lastChar = formula.charAt(formula.length - 1);
        realFormula = "=" == lastChar ? formula.substr(0, formula.length - 1) : formula;
        var result = "0";
        var symbol = "+";
        var len = realFormula.length;
        var numberStartPoint = 0;
        for (var i = 0; i < len; i++) {
          var _char = realFormula.charAt(i);
          if ("+" == _char || "-" == _char) {
            var number = realFormula.substr(numberStartPoint, i - numberStartPoint);
            switch (symbol) {
             case "+":
              result = this.add(result, number);
              break;

             case "-":
              result = this.sub(result, number);
            }
            symbol = _char;
            numberStartPoint = i + 1;
          }
        }
        if (numberStartPoint < len) {
          var _number = realFormula.substr(numberStartPoint, len - numberStartPoint);
          switch (symbol) {
           case "+":
            result = this.add(result, _number);
            break;

           case "-":
            result = this.sub(result, _number);
          }
        }
        console.log("\u8ba1\u7b97\u7ed3\u679c: " + result);
        return result;
      };
      return Computing;
    }();
    var test = {
      execute: function execute() {
        var c = new Computing();
        c.additionSubtraction("13+2-3=");
      }
    };
    module.exports = {
      Computing: Computing,
      test: test
    };
    cc._RF.pop();
  }, {} ],
  CMHttp: [ function(require, module, exports) {
    "use strict";
    cc._RF.push(module, "5f9cfi8qGlHAYophi/D1iDk", "CMHttp");
    "use strict";
    var _require = require("../event/CMEventMgr"), CMEventMgr = _require.CMEventMgr, EventTypeEnum = _require.EventTypeEnum;
    var ResponseEnum = cc.Enum({
      SUCCESS: "000",
      FAILURE: "-1"
    });
    var OVERTIME = 15e3;
    var _CMHttp = cc.Class({
      extends: cc.Component,
      statics: {
        isUri: function isUri(uri) {
          var regexUrl = /^(http|https):\/\/([^\/\:]+)(:\d+)?([^\?\#]*)(\?[^#]*)?(#[^\r\n]*)?$/;
          return "about:blank" === uri.toLowerCase() || regexUrl.test(uri);
        },
        urlRelativePath: function urlRelativePath(url) {
          if (url.indexOf("//") > 0) {
            var arrUrl = url.split("//");
            var start = arrUrl[1].indexOf("/");
            var refUrl = arrUrl[1].substring(start);
            var path = "";
            -1 != refUrl.indexOf("?") && (path = refUrl.split("?")[0]);
            -1 != path.indexOf("/") && (path = path.replace(/\//g, ""));
            return path;
          }
          return null;
        },
        urlQuery: function urlQuery(url) {
          var regex = /[^&=?]+=[^&]*/g;
          var res = url.match(regex);
          var result = {};
          function push(keyValue) {
            var index = keyValue.indexOf("=");
            if (index > 0) {
              var key = keyValue.substr(0, index);
              var value = keyValue.substr(index + 1);
              result[key] = value;
            }
          }
          for (var i = 0; i < res.length; i++) {
            var tKeyValue = res[i];
            push(tKeyValue);
          }
          return result;
        },
        uriChecker: function uriChecker(uri, data) {
          if (!data) return uri;
          var tRet = uri + "";
          tRet.indexOf("?") < 0 && (tRet += "?");
          data.timestamp = Date.now();
          var tKeys = Object.keys(data);
          for (var key in tKeys) {
            var pkey = tKeys[key];
            var pValue = encodeURIComponent(data[pkey]);
            tRet += "&" + pkey + "=" + pValue;
          }
          return tRet;
        },
        postChecker: function postChecker(data) {
          if ("object" !== typeof data) return "";
          var tRet = JSON.stringify(data);
          return tRet;
        },
        responseChecker: function responseChecker(strData) {
          if (!strData) return {
            code: ResponseEnum.FAILURE,
            msg: '""' + strData
          };
          try {
            tRet = JSON.parse(strData);
          } catch (err) {
            tRet = {
              code: ResponseEnum.FAILURE,
              msg: "params is error [" + strData.toString() + "]"
            };
          }
          return tRet;
        },
        get: function get(uri, data, callbackTarget, successHandler, failHandler, isLockScreen) {
          isLockScreen = !!isLockScreen;
          true == isLockScreen && CMEventMgr.emit({
            type: EventTypeEnum.BLOCK,
            from: "HTTP",
            uri: uri
          });
          var url = _CMHttp.uriChecker(uri, data);
          var xhr = new XMLHttpRequest();
          xhr.timeout = OVERTIME;
          xhr.open("GET", url);
          xhr.setRequestHeader("Content-type", "application/json;charset=utf-8");
          xhr.onerror = xhr.ontimeout = function() {
            cc.log(" HTTP Recv Get Msg \u5f02\u5e38 >>>>>>> url:" + url + " ret:" + xhr.response + " xhr.status:" + xhr.status);
            4 !== xhr.readyState && failHandler && failHandler.call(callbackTarget, xhr);
            true == isLockScreen && CMEventMgr.emit({
              type: EventTypeEnum.DEBLOCK,
              from: "HTTP",
              uri: uri
            });
          };
          xhr.onreadystatechange = function() {
            if (4 === xhr.readyState) {
              cc.log(" HTTP Recv Get Msg >>>>>>> url:" + url + " ret:" + xhr.response + " xhr.status:" + xhr.status);
              if (xhr.status >= 200 && xhr.status < 300 || 304 == xhr.status) {
                var tResponseData = _CMHttp.responseChecker(xhr.response);
                tResponseData.code == ResponseEnum.FAILURE ? failHandler && failHandler.call(callbackTarget, tResponseData) : successHandler && successHandler.call(callbackTarget, tResponseData);
              } else failHandler && failHandler.call(callbackTarget, _CMHttp.responseChecker(xhr.response));
              true == isLockScreen && CMEventMgr.emit({
                type: EventTypeEnum.DEBLOCK,
                from: "HTTP",
                uri: uri
              });
            }
          };
          xhr.send();
          cc.log(" HTTP Send Get Msg >>>>>>> url = " + url);
        },
        post: function post(uri, getData, postData, callbackTarget, successHandler, failHandler, isLockScreen) {
          isLockScreen = !!isLockScreen;
          true == isLockScreen && CMEventMgr.emit({
            type: EventTypeEnum.BLOCK,
            from: "HTTP",
            uri: uri
          });
          false == _CMHttp.isUri(uri) && (uri = APConfig.Environment.apiBaseUrl + uri);
          var url = _CMHttp.uriChecker(uri, getData);
          var xhr = new XMLHttpRequest();
          xhr.timeout = OVERTIME;
          xhr.open("POST", url);
          xhr.setRequestHeader("Content-type", "application/json;charset=utf-8");
          xhr.onerror = xhr.ontimeout = function() {
            if (4 !== xhr.readyState) {
              cc.log("HTTP Recv post msg >>>>>>> \u8d85\u65f6\u6216\u62a5\u9519\u7684\u94fe\u63a5 url = " + url + " xhr.status:" + xhr.status);
              failHandler && failHandler.call(callbackTarget, xhr);
            }
            true == isLockScreen && CMEventMgr.emit({
              type: EventTypeEnum.DEBLOCK,
              from: "HTTP",
              uri: uri
            });
          };
          xhr.onreadystatechange = function() {
            if (4 === xhr.readyState) {
              cc.log("HTTP Recv post msg >>>>>>>  url: " + url + " ret:" + xhr.responseText + " xhr.status:" + xhr.status);
              if (xhr.status >= 200 && xhr.status < 400) {
                var tResponseData = _CMHttp.responseChecker(xhr.responseText);
                tResponseData.code == ResponseEnum.FAILURE ? failHandler && failHandler.call(callbackTarget, tResponseData) : successHandler && successHandler.call(callbackTarget, tResponseData);
              } else failHandler && failHandler.call(callbackTarget, _CMHttp.responseChecker(xhr.responseText));
              true == isLockScreen && CMEventMgr.emit({
                type: EventTypeEnum.DEBLOCK,
                from: "HTTP",
                uri: uri
              });
            }
          };
          postData = _CMHttp.postChecker(postData);
          xhr.send(postData);
          cc.log(" HTTP Send POST MSG >>>>>>> url = " + url + " params = " + postData);
        }
      }
    });
    module.exports = {
      CMHttp: _CMHttp,
      ResponseEnum: ResponseEnum
    };
    cc._RF.pop();
  }, {
    "../event/CMEventMgr": "CMEventMgr"
  } ],
  CMLog: [ function(require, module, exports) {
    "use strict";
    cc._RF.push(module, "a86736Ien5NpbCMLlvmNh+e", "CMLog");
    "use strict";
    var _CMLog = cc.Class({
      properties: {},
      statics: {
        OpenLog: true,
        getDateString: function getDateString() {
          var d = new Date();
          var str = d.getHours().toString();
          var timeStr = "";
          timeStr += (1 == str.length ? "0" + str : str) + ":";
          str = d.getMinutes().toString();
          timeStr += (1 == str.length ? "0" + str : str) + ":";
          str = d.getSeconds().toString();
          timeStr += (1 == str.length ? "0" + str : str) + ":";
          str = d.getMilliseconds().toString();
          1 == str.length && (str = "00" + str);
          2 == str.length && (str = "0" + str);
          timeStr += str;
          timeStr = "[" + timeStr + "]";
          return timeStr;
        },
        stack: function stack(index) {
          var e = new Error();
          var lines = e.stack.split("\n");
          lines.shift();
          var result = [];
          lines.forEach(function(line) {
            line = line.substring(7);
            var lineBreak = line.split(" ");
            if (lineBreak.length < 2) result.push(lineBreak[0]); else {
              var _result$push;
              result.push((_result$push = {}, _result$push[lineBreak[0]] = lineBreak[1], _result$push));
            }
          });
          var list = [];
          if (index < result.length - 1) for (var a in result[index]) list.push(a);
          var splitList = list[0].split(".");
          return splitList[0] + ".js->" + splitList[1] + ": ";
        },
        log: function log() {
          for (var _len = arguments.length, args = new Array(_len), _key = 0; _key < _len; _key++) args[_key] = arguments[_key];
          var backLog = console.log || cc.log;
          _CMLog.OpenLog && backLog.call(this, "%s%s:" + cc.js.formatStr.apply(cc, arguments), _CMLog.stack(2), _CMLog.getDateString());
        },
        info: function info() {
          for (var _len2 = arguments.length, args = new Array(_len2), _key2 = 0; _key2 < _len2; _key2++) args[_key2] = arguments[_key2];
          var backLog = console.log || cc.log;
          _CMLog.OpenLog && backLog.call(this, "%c%s%s:" + cc.js.formatStr.apply(cc, arguments), "color:#00CD00;", _CMLog.stack(2), _CMLog.getDateString());
        },
        warn: function warn() {
          for (var _len3 = arguments.length, args = new Array(_len3), _key3 = 0; _key3 < _len3; _key3++) args[_key3] = arguments[_key3];
          var backLog = console.log || cc.log;
          _CMLog.OpenLog && backLog.call(this, "%c%s%s:" + cc.js.formatStr.apply(cc, arguments), "color:#ee7700;", _CMLog.stack(2), _CMLog.getDateString());
        },
        error: function error() {
          for (var _len4 = arguments.length, args = new Array(_len4), _key4 = 0; _key4 < _len4; _key4++) args[_key4] = arguments[_key4];
          var backLog = console.log || cc.log;
          _CMLog.OpenLog && backLog.call(this, "%c%s%s:" + cc.js.formatStr.apply(cc, arguments), "color:red", _CMLog.stack(2), _CMLog.getDateString());
        }
      }
    });
    module.exports = _CMLog;
    cc._RF.pop();
  }, {} ],
  CMMain: [ function(require, module, exports) {
    "use strict";
    cc._RF.push(module, "87349L0h3lKaq4OqWw7G8S5", "CMMain");
    "use strict";
    require("./platform/CMPlatformClient");
    require("./core/utils/MathUtils");
    require("./core/utils/StringUtils");
    window.UIUtils = require("./core/utils/UIUtils");
    window.CMUIManager = require("./core/manager/CMUIManager");
    window.CMLog = require("./core/log/CMLog");
    window.CMEventMgr = require("./core/event/CMEventMgr").CMEventMgr;
    window.CMConfig = require("./CMConfig");
    var _require = require("./core/net/CMHttp"), CMHttp = _require.CMHttp;
    var _require2 = require("./mathland/calculate/CMFormulaForComputing"), Computing = _require2.Computing;
    cc.Class({
      extends: cc.Component,
      properties: {},
      onLoad: function onLoad() {
        cc.game.addPersistRootNode(this.node);
        CMPlatformClient.callNativeLaunchComplete();
      },
      onClick: function onClick() {
        console.log("onClick");
        CMPlatformClient.getUserInfoFromNative();
      },
      start: function start() {}
    });
    cc._RF.pop();
  }, {
    "./CMConfig": "CMConfig",
    "./core/event/CMEventMgr": "CMEventMgr",
    "./core/log/CMLog": "CMLog",
    "./core/manager/CMUIManager": "CMUIManager",
    "./core/net/CMHttp": "CMHttp",
    "./core/utils/MathUtils": "MathUtils",
    "./core/utils/StringUtils": "StringUtils",
    "./core/utils/UIUtils": "UIUtils",
    "./mathland/calculate/CMFormulaForComputing": "CMFormulaForComputing",
    "./platform/CMPlatformClient": "CMPlatformClient"
  } ],
  CMMessage: [ function(require, module, exports) {
    "use strict";
    cc._RF.push(module, "dbf41iyRutCeYc7+swgqoPG", "CMMessage");
    "use strict";
    var APMessage = cc.Class({
      properties: {
        msgId: 0,
        msg: null,
        timestamp: {
          default: Date.now()
        }
      }
    });
    cc._RF.pop();
  }, {} ],
  CMPlatformClient: [ function(require, module, exports) {
    "use strict";
    cc._RF.push(module, "2c5337yNndNn6nRuvmzotzQ", "CMPlatformClient");
    "use strict";
    window.CMPlatformClient = {
      iOSClassName: "CMJSCallObjectC",
      iOSLaunchClassName: "CMCocosManager",
      androidClassName: "org/codemao/mathland/CMJSCallJava",
      callNativeLaunchComplete: function callNativeLaunchComplete() {
        var retStr = void 0;
        cc.sys.os == cc.sys.OS_IOS ? retStr = jsb.reflection.callStaticMethod(this.iOSLaunchClassName, "gameSceneLoadcomplete:", true) : cc.sys.os == cc.sys.OS_ANDROID && (retStr = jsb.reflection.callStaticMethod(this.androidClassName, "gameSceneLoadcomplete", "(Z)V", true));
        console.log(retStr);
      },
      getUserInfoFromNative: function getUserInfoFromNative(callback) {
        cc.log("Taylor >>> \u8c03\u7528\u539f\u751f\u5c42");
        var retStr = void 0;
        cc.sys.os == cc.sys.OS_IOS ? retStr = jsb.reflection.callStaticMethod(this.iOSClassName, "click") : cc.sys.os == cc.sys.OS_ANDROID && (retStr = jsb.reflection.callStaticMethod(this.androidClassName, "getUserInfo", "()Ljava/lang/String"));
        return retStr ? JSON.parse(retStr) : "";
      },
      testEvalString: function testEvalString(strParams) {
        console.log("\u6d4b\u8bd5 Native \u8c03\u7528 JS");
        console.log("\u53c2\u6570:", strParams);
        return "\u6210\u529f\u6210\u529f";
      },
      callNativeQuit: function callNativeQuit() {
        var retStr = void 0;
        cc.sys.os == cc.sys.OS_IOS ? retStr = jsb.reflection.callStaticMethod(this.iOSClassName, "click") : cc.sys.os == cc.sys.OS_ANDROID && (retStr = jsb.reflection.callStaticMethod(this.androidClassName, "getUserInfo"));
        return retStr ? JSON.parse(retStr) : "";
      }
    };
    cc._RF.pop();
  }, {} ],
  CMSocketMgr: [ function(require, module, exports) {
    "use strict";
    cc._RF.push(module, "75d22hglfJBgo7idbaDTBtc", "CMSocketMgr");
    "use strict";
    var CMSocket = require("./CMSocket");
    var SocketType = cc.Enum({
      HALL: "HALL",
      ROOM: "ROOM"
    });
    var CMSocketMgr = {
      socketMap: {},
      getSocket: function getSocket(socketType) {
        if (!this.socketMap[socketType]) {
          var tCMSocket = new CMSocket();
          tCMSocket.socketType = socketType;
          this.socketMap[socketType] = tCMSocket;
        }
        return this.socketMap[socketType];
      },
      connect: function connect(socketType, url) {
        var tCMSocket = this.getSocket(socketType);
        tCMSocket && tCMSocket.connect(url);
      },
      sendMsg: function sendMsg(socketType, msg, callback) {
        var tCMSocket = this.getSocket(socketType);
        tCMSocket && tCMSocket.send(msg, callback);
      },
      disconnect: function disconnect(socketType, msg) {
        var tCMSocket = this.getSocket(socketType);
        tCMSocket && tCMSocket.disconnect();
      }
    };
    module.exports = {
      CMSocketMgr: CMSocketMgr,
      SocketType: SocketType
    };
    cc._RF.pop();
  }, {
    "./CMSocket": "CMSocket"
  } ],
  CMSocket: [ function(require, module, exports) {
    "use strict";
    cc._RF.push(module, "27e1c+L7Y9NRZ1tP7qleqXf", "CMSocket");
    "use strict";
    var _require = require("../../event/CMEventMgr"), CMEventMgr = _require.CMEventMgr, EventTypeEnum = _require.EventTypeEnum;
    var INTERVAL = {
      PING: 1e4,
      ACK: 3e3,
      MAX_RESEND_COUNT: 5,
      CLEAR_RECREQUEUE: 18e4
    };
    var CMSocketStateEnum = cc.Enum({
      CONNECTING: "CONNECTING",
      CONNECT_SUCCESS: "CONNECT_SUCCESS",
      CONNECT_DISCONNECT: "CONNECT_DISCONNECT",
      CONNECT_ERROR: "CONNECT_ERROR",
      CONNECT_CLOSE: "CONNECT_CLOSE"
    });
    var MSG_ID = {
      PING: 1
    };
    var REQ_RESULT = cc.Enum({
      SUCCESS: 1,
      FAILURE: -1
    });
    function isMatchAddress() {
      if (!this.address) return false;
      if ("" == this.address) return false;
      return true;
    }
    var _CMSocket = cc.Class({
      properties: {
        recvReqQueue: {
          default: {}
        },
        waitAckQueue: {
          default: {}
        },
        waitSendQueue: {
          default: []
        },
        msgNo: 0,
        lastSendMsgDate: Date.now(),
        timer: null,
        sendPause: false,
        isVerify: false,
        socketType: {
          default: void 0,
          type: cc.String
        },
        address: {
          default: "ws://47.74.152.97:8080"
        },
        wssCacertAsset: {
          default: null,
          type: cc.Asset
        },
        socket: {
          type: WebSocket,
          default: null
        }
      },
      connect: function connect(url) {
        if (false == isMatchAddress.call(this)) throw "\u8bf7\u4f7f\u7528\u6b63\u786e\u7684websocket\u5730\u5740:" + this.address;
        this.disconnect();
        this.address = url;
        this.msgNo = 0;
        this.socket || (this.socket = new WebSocket(this.address, []));
        this.socket.binaryType = "blob";
        this.socket.onopen = this.onSocketOpen.bind(this);
        this.socket.onmessage = this.onSocketMessage.bind(this);
        this.socket.onerror = this.onSocketError.bind(this);
        this.socket.onclose = this.onSocketClose.bind(this);
        CMEventMgr.emit({
          type: CMSocketStateEnum.CONNECTING,
          socketType: this.socketType
        });
      },
      disconnect: function disconnect() {
        if (this.socket) {
          this.socket.close();
          this.socket.onopen = null;
          this.socket.onmessage = null;
          this.socket.onerror = null;
          this.socket.onclose = null;
          this.socket = null;
        }
        if (this.timer) {
          clearInterval(this.timer);
          this.timer = null;
        }
        this.recvReqQueue = {};
        this.waitAckQueue = {};
        this.waitSendQueue.length = 0;
        this.lastSendMsgDate = Date.now();
        this.msgNo = 0;
        this.sendPause = false;
      },
      reconnect: function reconnect() {
        this.connect(this.address);
      },
      onSocketOpen: function onSocketOpen(event) {
        console.log("WebSocket have connected, the SocketType is " + this.socketType.toString());
        this.timer = setInterval(this.msgTick.bind(this), 1e3, this);
        CMEventMgr.emit({
          type: CMSocketStateEnum.CONNECT_SUCCESS,
          socketType: this.socketType
        });
      },
      onSocketMessage: function onSocketMessage(event) {
        cc.log(this.socketType + " Socket recieve data " + event.data);
        var tRecvData = JSON.parse(event.data);
        tRecvData.protocolNum ? this.recvMessage(tRecvData) : CMEventMgr.emit({
          type: "MSG_ERROR",
          data: tRecvData,
          socketType: this.socketType
        });
        this.lastSendMsgDate = Date.now();
      },
      onSocketError: function onSocketError(event) {
        cc.log("WebSocket \u94fe\u63a5\u62a5\u9519:" + this.socketType);
        this.disconnect();
        CMEventMgr.emit({
          type: CMSocketStateEnum.CONNECT_ERROR,
          socketType: this.socketType
        });
      },
      onSocketClose: function onSocketClose(event) {
        cc.log("WebSocket \u94fe\u63a5\u5173\u95ed:" + this.socketType);
        this.disconnect();
        CMEventMgr.emit({
          type: CMSocketStateEnum.CONNECT_CLOSE,
          socketType: this.socketType
        });
      },
      send: function send(msg, callback) {
        if (!msg || !this.socket) {
          cc.log(">>>>>>>>  \u3010WebSocket\u3011\u9700\u8981\u521d\u59cb\u5316,\u624d\u53ef\u4ee5\u53d1\u9001\u6570\u636e");
          return;
        }
        if (this.socket.readyState != WebSocket.OPEN) {
          cc.log(">>>>>>>>  \u3010WebSocket\u3011\u5f53\u524d\u6ca1\u6709\u5904\u4e8e\u8fde\u63a5\u72b6\u6001");
          return;
        }
        var tMessge = {};
        tMessge.msgNo = msg.protocolNum;
        tMessge.timestamp = Date.now();
        tMessge.data = msg;
        tMessge.resend_count = 0;
        tMessge.acceptCallBack = callback;
        if (true == this.sendPause) this.waitSendQueue.push(tMessge); else {
          this.waitAckQueue[tMessge.msgNo] = tMessge;
          this.socket.send(JSON.stringify(msg));
          cc.log(this.socketType + " Socket send data " + JSON.stringify(msg));
        }
        this.lastSendMsgDate = Date.now();
      },
      resumeSend: function resumeSend() {
        if (!this.socket) return;
        var len = this.waitSendQueue.length;
        if (len > 0) {
          for (var i = 0; i < len; i++) {
            var tMessage = this.waitSendQueue[i];
            tMessage && this.socket.send(JSON.stringify(tMessage.data));
          }
          this.waitSendQueue = [];
        }
      },
      recvMessage: function recvMessage(data) {
        var tProtocolStr = data.protocolNum.toString();
        if (this.waitAckQueue[tProtocolStr]) {
          var tMessage = this.waitAckQueue[tProtocolStr];
          tMessage.acceptCallBack && tMessage.acceptCallBack(data);
          delete this.waitAckQueue[tProtocolStr];
          if (true == this.sendPause) {
            this.sendPause = false;
            this.resumeSend();
          }
        }
        this.recvReqQueue[tProtocolStr] && (this.recvReqQueue[tProtocolStr] = Date.now());
        if (tProtocolStr == MSG_ID.PING.toString()) cc.log("\u5fc3\u8df3 socketType:" + this.socketType); else {
          var tEventType = tProtocolStr;
          CMEventMgr.emit({
            type: tEventType,
            data: data,
            socketType: this.socketType
          });
        }
      },
      clearRecvReqQueue: function clearRecvReqQueue() {
        if (!this.socket) return;
        var now = Date.now();
        for (var key in this.recvReqQueue) now - this.recvReqQueue[key] > INTERVAL.CLEAR_RECREQUEUE && delete this.recvReqQueue[key];
      },
      msgTick: function msgTick(self) {
        var now = Date.now();
        now - this.lastSendMsgDate > INTERVAL.PING && this.send({
          protocolNum: MSG_ID.PING
        });
        for (var key in this.waitAckQueue) {
          var tMessage = this.waitAckQueue[key];
          if (now - tMessage.timestamp > INTERVAL.ACK) {
            if (tMessage.resend_count > INTERVAL.MAX_RESEND_COUNT) {
              CMEventMgr.emit({
                type: CMSocketStateEnum.CONNECT_DISCONNECT,
                socketType: this.socketType
              });
              this.reconnect();
              break;
            }
            tMessage.timestamp = Date.now();
            tMessage.resend_count += 1;
            this.socket.send(JSON.stringify(tMessage.data));
            this.sendPause = true;
          }
        }
      }
    });
    module.exports = {
      CMSocket: _CMSocket,
      REQ_RESULT: REQ_RESULT,
      INTERVAL: INTERVAL
    };
    cc._RF.pop();
  }, {
    "../../event/CMEventMgr": "CMEventMgr"
  } ],
  CMTimeManager: [ function(require, module, exports) {
    "use strict";
    cc._RF.push(module, "4b2c0Iw2KNOsb72aRvZ8uKC", "CMTimeManager");
    "use strict";
    cc.Class({
      extends: cc.Component,
      properties: {},
      start: function start() {}
    });
    cc._RF.pop();
  }, {} ],
  CMUIHelp: [ function(require, module, exports) {
    "use strict";
    cc._RF.push(module, "ebf42zePM9OwqgIqYw8Mt7H", "CMUIHelp");
    "use strict";
    var CMUIManager = require("../core/manager/CMUIManager");
    var CMUIHelp = {
      showTip: function showTip(message, delay) {}
    };
    module.exports = CMUIHelp;
    cc._RF.pop();
  }, {
    "../core/manager/CMUIManager": "CMUIManager"
  } ],
  CMUIManager: [ function(require, module, exports) {
    "use strict";
    cc._RF.push(module, "e41f4rMmjlFz4TrdlzMBUca", "CMUIManager");
    "use strict";
    var CMLog = require("../log/CMLog");
    var BaseUI = require("../baseUi/BaseUI");
    var _CMUIManager = {
      _uiList: [],
      _uiRoot: null,
      _checkRoot: function _checkRoot() {
        if (!this._uiRoot) {
          CMLog.warn(">>>> CMUIManager [ _checkRoot] _uiRoot\u4e0d\u5b58\u5728\uff0c\u66ff\u6362\u4e3aCanvas\u5c42\u7ea7 ");
          this._uiRoot = cc.find("Canvas");
        }
      },
      getUIRoot: function getUIRoot() {
        this._checkRoot();
        return this._uiRoot;
      },
      setUIRoot: function setUIRoot(uiRoot) {
        this._uiRoot = uiRoot;
        this._checkRoot();
      },
      openUI: function openUI(uiClassName, zOrder, callback, onProgress) {
        var _this = this;
        for (var _len = arguments.length, args = new Array(_len > 4 ? _len - 4 : 0), _key = 4; _key < _len; _key++) args[_key - 4] = arguments[_key];
        if (this.getUI(uiClassName)) {
          CMLog.warn(uiClassName + ">>> UI\u5df2\u7ecf\u5b58\u5728");
          return;
        }
        cc.resources.load(uiClass.getUrl(), cc.Prefab, function(completeCount, totalCount, item) {
          onProgress ? onProgress(completeCount, totalCount, item) : null;
        }, function(error, prefab) {
          if (error) {
            cc.log(error);
            return;
          }
          if (_this.getUI(uiClass)) return;
          var uiNode = cc.instantiate(prefab);
          uiNode.parent = _this.uiRoot;
          zOrder && (uiNode.zIndex = zOrder);
          var ui = uiNode.getComponent(uiClass);
          ui.tag = uiClass;
          _this.uiList.push(ui);
          callback && callback(ui, args);
        });
      },
      closeUI: function closeUI(uiClassName) {
        for (var i = 0; i < this._uiList.length; i++) if (this._uiList[i].mTag == uiClassName) {
          this._uiList[i].node.destroy();
          this._uiList.splice(i, 1);
          break;
        }
      },
      showUI: function showUI(uiClassName, callback) {
        var _this2 = this;
        var uiComponent = this.getUI(uiClassName);
        if (uiComponent) {
          uiComponent.node.active = true;
          uiComponent.onShow();
          callback && callback(uiComponent);
        } else this.openUI(uiClassName, 0, function() {
          var uiComponent = _this2.getUI(uiClassName);
          uiComponent.onShow();
          callback && callback(uiComponent);
        });
      },
      hideUI: function hideUI(uiClassName, callback) {
        var uiComponent = this.getUI(uiClassName);
        if (uiComponent) {
          uiComponent.node.active = false;
          uiComponent.onHide();
          callback && callback(uiComponent);
        }
      },
      getUI: function getUI(uiClassName) {
        var retNode = null;
        for (var i = 0; i < this._uiList.length; i++) if (this._uiList[i].mTag == uiClassName) {
          retNode = this._uiList[i];
          break;
        }
        return retNode;
      }
    };
    module.exports = _CMUIManager;
    cc._RF.pop();
  }, {
    "../baseUi/BaseUI": "BaseUI",
    "../log/CMLog": "CMLog"
  } ],
  ElementConst: [ function(require, module, exports) {
    "use strict";
    cc._RF.push(module, "880e1HVdTdBUri0SHoPZY0D", "ElementConst");
    "use strict";
    var ElementSize = {
      HotpotSize: cc.size(44, 44),
      NumberSize: cc.size(44, 44),
      SymbolSize: cc.size(44, 44),
      MaterialSize: cc.size(44, 44),
      ToolSize: cc.size(44, 44),
      MinFolderSize: cc.size(44, 44),
      MaxFolderSize: cc.size(44, 44)
    };
    var ElementCategoryEnum = cc.Enum({
      None: 0,
      NumberCategory: 1,
      SymbolCategory: 2,
      MaterialCategory: 3,
      ToolCategory: 4
    });
    var NumberEnum = cc.Enum({
      Number0: 0,
      Number1: 1,
      Number2: 2,
      Number3: 3,
      Number4: 4,
      Number5: 5,
      Number6: 6,
      Number7: 7,
      Number8: 8,
      Number9: 9
    });
    var SymbolEnum = cc.Enum({
      Add: 0,
      Sub: 1,
      Less: 2,
      More: 3,
      Equal: 4
    });
    var SymbolResPath = [ "mathland/symbolPanel/symbol_add", "mathland/symbolPanel/symbol_sub", "mathland/symbolPanel/symbol_left", "mathland/symbolPanel/symbol_right", "mathland/symbolPanel/symbol_equal" ];
    var MateralEnum = cc.Enum({
      Cake: 0,
      StrawBerry: 1,
      WaterMelon: 2
    });
    var MateralResPath = [ "mathland/materialPanel/cake", "mathland/materialPanel/strawberry", "mathland/materialPanel/watermelon" ];
    var ToolEnum = cc.Enum({
      Count: 0,
      Louds: 1,
      Category: 2
    });
    var ToolResPath = [ "mathland/toolsPanel/icon_count", "mathland/toolsPanel/icon_louds", "mathland/toolsPanel/icon_categ" ];
    module.exports = {
      ElementSize: ElementSize,
      ElementCategoryEnum: ElementCategoryEnum,
      NumberEnum: NumberEnum,
      SymbolEnum: SymbolEnum,
      MateralEnum: MateralEnum,
      ToolEnum: ToolEnum,
      SymbolResPath: SymbolResPath,
      MateralResPath: MateralResPath,
      ToolResPath: ToolResPath
    };
    cc._RF.pop();
  }, {} ],
  ElementInfo: [ function(require, module, exports) {
    "use strict";
    cc._RF.push(module, "ea7bff+fsRMt6eeVcqqnONs", "ElementInfo");
    "use strict";
    var _require = require("./ElementConst"), ElementCategoryEnum = _require.ElementCategoryEnum;
    var ElementStateEnum = cc.Enum({
      None: 0,
      Born: 1,
      Normal: 2
    });
    var ElementInfo = cc.Class({
      properties: {
        eleId: -1,
        eleCategory: {
          default: ElementCategoryEnum.None,
          type: ElementCategoryEnum,
          tooltip: "\u5143\u7d20\u7c7b\u522b"
        },
        eleType: {
          default: -1,
          tooltip: "\u5143\u7d20\u7c7b\u522b\u786e\u5b9a\u540e \u5143\u7d20\u7c7b\u578b"
        },
        eleState: {
          default: ElementStateEnum.None,
          type: ElementStateEnum,
          tooltip: "\u662f\u5426folder"
        },
        numValue: {
          default: 0,
          tooltip: "\u6570\u5b57"
        },
        strValue: {
          default: "",
          type: cc.String,
          tooltip: "\u5b57\u7b26\u4e32\u503c"
        },
        _preElementInfo: {
          default: null,
          type: ElementInfo,
          tooltip: "\u6307\u5411\u524d\u4e00\u4e2a\u5143\u7d20"
        },
        _nextElementInfo: {
          default: null,
          type: ElementInfo,
          tooltip: "\u6307\u5411\u540e\u4e00\u4e2a\u5143\u7d20"
        }
      }
    });
    ElementInfo.ElementStateEnum = ElementStateEnum;
    module.exports = ElementInfo;
    cc._RF.pop();
  }, {
    "./ElementConst": "ElementConst"
  } ],
  ElementMgr: [ function(require, module, exports) {
    "use strict";
    cc._RF.push(module, "0f9435okgpFHYz7aBMjleqE", "ElementMgr");
    "use strict";
    var CMLog = require("../../core/log/CMLog");
    var ElementUI = require("./ElementUI");
    var ElementInfo = require("./ElementInfo");
    var _require = require("./ElementConst"), ElementCategoryEnum = _require.ElementCategoryEnum, NumberEnum = _require.NumberEnum, SymbolEnum = _require.SymbolEnum, ToolEnum = _require.ToolEnum, MateralEnum = _require.MateralEnum, ElementSize = _require.ElementSize;
    var ElementMgr = {
      _elementId: 0,
      _allElements: [],
      generateElementID: function generateElementID() {
        var retId = this._elementId;
        this._elementId = this._elementId + 1;
        return retId;
      },
      _getPrefabResPath: function _getPrefabResPath(eleCategory) {
        switch (eleCategory) {
         case ElementCategoryEnum.NumberCategory:
         case ElementCategoryEnum.SymbolCategory:
         case ElementCategoryEnum.MaterialCategory:
         case ElementCategoryEnum.ToolCategory:
          return "prefab/element/ElementUI";

         default:
          CMLog.warn("\u65e0\u6cd5\u627e\u5230\u9884\u652f\u4f53 eleCategory:", eleCategory);
          return "";
        }
      },
      createElement: function createElement(eleCategory, eleType, parentNode, pos, callback) {
        var _this = this;
        var resPath = this._getPrefabResPath(eleCategory);
        CMLog.log("\u52a0\u8f7d\u5143\u7d20\u9884\u5236\u4f53", resPath);
        var self = this;
        cc.resources.load(resPath, cc.Prefab, function(err, prefab) {
          var elePrefab = cc.instantiate(prefab);
          var elementUI = elePrefab.getComponent("ElementUI");
          elementUI.eleInfo || (elementUI.eleInfo = new ElementInfo());
          elementUI.eleInfo.eleCategory = eleCategory;
          elementUI.eleInfo.eleType = eleType;
          elementUI.eleInfo.eleId = self.generateElementID();
          elementUI.eleInfo.eleState = ElementInfo.ElementStateEnum.Born;
          elePrefab.parent = parentNode;
          elePrefab.position = pos;
          _this._allElements.push(elementUI);
          callback && callback(elementUI);
        });
      },
      findElementUIById: function findElementUIById(eleId) {
        for (var i = 0; i < this._allElements.length; i++) if (this._allElements[i].eleId == eleId) return this._allElements[i];
        return null;
      },
      deleteElementUIById: function deleteElementUIById(eleId) {
        for (var i = 0; i < this._allElements.length; i++) if (this._allElements[i].eleId == eleId) {
          this._allElements.splice(i, 1);
          console.log("\u5220\u9664" + eleId + "\u6210\u529f");
          break;
        }
      }
    };
    module.exports = ElementMgr;
    cc._RF.pop();
  }, {
    "../../core/log/CMLog": "CMLog",
    "./ElementConst": "ElementConst",
    "./ElementInfo": "ElementInfo",
    "./ElementUI": "ElementUI"
  } ],
  ElementUI: [ function(require, module, exports) {
    "use strict";
    cc._RF.push(module, "ea3f9EnvYhALKJJVUyB8wNV", "ElementUI");
    "use strict";
    var _require = require("./ElementConst"), ElementCategoryEnum = _require.ElementCategoryEnum, NumberEnum = _require.NumberEnum, SymbolEnum = _require.SymbolEnum, MaterialEnum = _require.MaterialEnum, ToolEnum = _require.ToolEnum;
    var _require2 = require("./ElementConst"), SymbolResPath = _require2.SymbolResPath, MateralResPath = _require2.MateralResPath, ToolResPath = _require2.ToolResPath;
    var ElementInfo = require("./ElementInfo");
    var ElementUI = cc.Class({
      extends: cc.Component,
      properties: {
        eleInfo: {
          default: null,
          type: ElementInfo,
          tooltip: "\u5143\u7d20\u7c7b\u578b",
          serializable: false
        }
      },
      onLoad: function onLoad() {
        this.allChilds = UIUtils.getAllChildren(this.node);
        this.changeCategory();
      },
      _changeContentNodeActive: function _changeContentNodeActive(numActive, spActive) {
        this.allChilds.numLabel.active = numActive;
        this.allChilds.iconSp.active = spActive;
      },
      changeNumType: function changeNumType(numValue) {
        CMLog.log("\u6539\u53d8\u6570\u503c:", numValue);
        numValue = numValue || this.eleInfo.eleType;
        this.allChilds.numLabel.getComponent(cc.Label).string = numValue;
      },
      changeSpType: function changeSpType(eleType, resPath) {
        var _this = this;
        this.eleInfo.eleType = eleType;
        cc.resources.load(resPath, cc.SpriteFrame, function(err, spriteFrame) {
          if (err) {
            CMLog.error(err);
            return;
          }
          _this.allChilds.iconSp.getComponent(cc.Sprite).spriteFrame = spriteFrame;
        });
      },
      changeState: function changeState(state) {
        this.eleInfo.state = state;
      },
      changeCategory: function changeCategory() {
        switch (this.eleInfo.eleCategory) {
         case ElementCategoryEnum.NumberCategory:
          this._changeContentNodeActive(true, false);
          this.changeNumType();
          break;

         case ElementCategoryEnum.SymbolCategory:
          this._changeContentNodeActive(false, true);
          this.changeSpType(this.eleInfo.eleType, SymbolResPath[this.eleInfo.eleType]);
          break;

         case ElementCategoryEnum.MaterialCategory:
          this._changeContentNodeActive(false, true);
          this.changeSpType(this.eleInfo.eleType, MateralResPath[this.eleInfo.eleType]);
          break;

         case ElementCategoryEnum.ToolCategory:
          this._changeContentNodeActive(false, true);
          this.changeSpType(this.eleInfo.eleType, ToolResPath[this.eleInfo.eleType]);
        }
      },
      addEvent: function addEvent(touchStart, touchMove, touchEnded, touchCancel) {
        this.node.on(cc.Node.EventType.TOUCH_START, touchStart, this);
        this.node.on(cc.Node.EventType.TOUCH_MOVE, touchMove, this);
        this.node.on(cc.Node.EventType.TOUCH_END, touchEnded, this);
        this.node.on(cc.Node.EventType.TOUCH_CANCEL, touchCancel, this);
      },
      removeEvent: function removeEvent() {
        this.node.targetOff();
      }
    });
    module.exports = ElementUI;
    cc._RF.pop();
  }, {
    "./ElementConst": "ElementConst",
    "./ElementInfo": "ElementInfo"
  } ],
  MaterialPanel: [ function(require, module, exports) {
    "use strict";
    cc._RF.push(module, "53061s+ISRKBIk+XMCw1kgJ", "MaterialPanel");
    "use strict";
    var _require = require("./element/ElementConst"), ElementCategoryEnum = _require.ElementCategoryEnum, MateralEnum = _require.MateralEnum;
    var CMBaseUI = require("./CMBaseUI");
    cc.Class({
      extends: CMBaseUI,
      properties: {},
      onLoad: function onLoad() {
        this.allChilds = UIUtils.getAllChildren(this.node);
        this.allChilds.cake.on(cc.Node.EventType.TOUCH_START, this.onTouchStart, this);
        this.allChilds.strawberry.on(cc.Node.EventType.TOUCH_START, this.onTouchStart, this);
        this.allChilds.watermelon.on(cc.Node.EventType.TOUCH_START, this.onTouchStart, this);
      },
      start: function start() {},
      onTouchStart: function onTouchStart(event) {
        var glPos = event.getLocation();
        var eleCategory = ElementCategoryEnum.MaterialCategory;
        var eleType = -1;
        event.currentTarget == this.allChilds.cake ? eleType = MateralEnum.Cake : event.currentTarget == this.allChilds.strawberry ? eleType = MateralEnum.StrawBerry : event.currentTarget == this.allChilds.watermelon && (eleType = MateralEnum.WaterMelon);
        CMEventMgr.emit({
          type: "BORN_ELEMENT",
          eleCategory: eleCategory,
          eleType: eleType,
          pos: glPos
        });
        this.node.destroy();
      }
    });
    cc._RF.pop();
  }, {
    "./CMBaseUI": "CMBaseUI",
    "./element/ElementConst": "ElementConst"
  } ],
  MathElementCtr: [ function(require, module, exports) {
    "use strict";
    cc._RF.push(module, "c499aocWJtFPK03tYNa/6x6", "MathElementCtr");
    "use strict";
    var _selectedElement;
    var ElementConst = require("../ui/element/ElementConst");
    var ElementInfo = require("../ui/element/ElementInfo");
    var ElementUI = require("../ui/element/ElementUI");
    var ElementMgr = require("../ui/element/ElementMgr");
    var CMLog = require("../core/log/CMLog");
    var MathElementCtr = cc.Class({
      extends: cc.Component,
      properties: {
        touchLayer: {
          default: null,
          type: cc.Node,
          tooltip: "\u89e6\u6478\u5c42\u7ea7"
        },
        containerLayer: {
          default: null,
          type: cc.Node,
          tooltip: "\u5bb9\u5668\u5c42\u7ea7"
        },
        _selectedElement: (_selectedElement = {
          default: null,
          type: ElementUI
        }, _selectedElement["default"] = "\u5f53\u524d\u9009\u4e2d\u7684\u5143\u7d20", _selectedElement.serializable = false, 
        _selectedElement)
      },
      onLoad: function onLoad() {
        CMEventMgr.on("BORN_ELEMENT", this.handleBorn.bind(this));
        this.touchLayer.on(cc.Node.EventType.TOUCH_MOVE, this.handleMove, this);
        this.touchLayer.on(cc.Node.EventType.TOUCH_END, this.handleEnd, this);
        this.touchLayer.on(cc.Node.EventType.TOUCH_CANCEL, this.handleEnd, this);
        this.touchLayer._touchListener.setSwallowTouches(false);
      },
      onStart: function onStart() {},
      handleBorn: function handleBorn(event) {
        var _this = this;
        var pos = this.touchLayer.convertToNodeSpaceAR(event.pos);
        ElementMgr.createElement(event.eleCategory, event.eleType, this.touchLayer, pos, function(elementUI) {
          CMLog.log("\u521b\u5efa\u6210\u529f:", elementUI);
          elementUI.addEvent(_this.touchElementStart.bind(_this), _this.touchElementMove.bind(_this), _this.touchElementEnd.bind(_this), _this.touchElementCancel.bind(_this));
          _this._selElementComponent = elementUI;
        });
      },
      handleMove: function handleMove(event) {
        this._selElementComponent && this._selElementComponent.eleInfo.eleState == ElementInfo.ElementStateEnum.Born && this.touchElementMove(event);
      },
      handleEnd: function handleEnd(event) {
        if (this._selElementComponent && this._selElementComponent.eleInfo.eleState == ElementInfo.ElementStateEnum.Born) {
          this.addElementToContainer(this._selElementComponent, cc.v2(this._selElementComponent.position));
          this._selElementComponent.changeState(ElementInfo.ElementStateEnum.Normal);
          this._selElementComponent = null;
        }
      },
      addElementToContainer: function addElementToContainer(ele, pos) {
        var pos = ele.node.convertToWorldSpaceAR(pos);
        pos = this.containerLayer.convertToNodeSpaceAR(pos);
        ele.node.parent = this.containerLayer;
        ele.position = pos;
      },
      touchElementStart: function touchElementStart(event) {
        CMLog.log(">> handleTouchStart");
        if (this._selElementComponent && this._selElementComponent.eleInfo.eleState == ElementInfo.ElementStateEnum.Born) return;
        self._selElementComponent = event.currentTarget.getComponent("ElementUI");
      },
      touchElementMove: function touchElementMove(event) {
        CMLog.log(">> handleTouchMove");
        if (this._selElementComponent) {
          var pos = this._selElementComponent.node.position;
          pos.addSelf(event.touch.getDelta());
          this._selElementComponent.node.position = pos;
        }
      },
      touchElementEnd: function touchElementEnd(event) {
        CMLog.log(">> handleTouchEnd");
        this._selElementComponent;
      },
      touchElementCancel: function touchElementCancel(event) {
        this._selElementComponent;
      }
    });
    cc._RF.pop();
  }, {
    "../core/log/CMLog": "CMLog",
    "../ui/element/ElementConst": "ElementConst",
    "../ui/element/ElementInfo": "ElementInfo",
    "../ui/element/ElementMgr": "ElementMgr",
    "../ui/element/ElementUI": "ElementUI"
  } ],
  MathLand: [ function(require, module, exports) {
    "use strict";
    cc._RF.push(module, "53072cioWxJSKqPwAkwQn+v", "MathLand");
    "use strict";
    var UIUtils = require("../core/utils/UIUtils");
    var MathLand = cc.Class({
      extends: cc.Component,
      properties: {
        childs: {
          default: [],
          type: cc.Node,
          tooltip: "\u5b50\u8282\u70b9\u6570\u7ec4",
          visible: false
        }
      },
      onLoad: function onLoad() {
        this.childs = UIUtils.getAllChildren(cc.find("Canvas"));
        this._addOperateEventListener();
      },
      _addOperateEventListener: function _addOperateEventListener() {
        this.childs.btnNumber.on(cc.Node.EventType.TOUCH_END, this.onClickNumber, this);
        this.childs.btnSymbol.on(cc.Node.EventType.TOUCH_END, this.onClickSymbol, this);
        this.childs.btnMaterial.on(cc.Node.EventType.TOUCH_END, this.onClickMaterial, this);
        this.childs.btnTools.on(cc.Node.EventType.TOUCH_END, this.onClickTools, this);
        this.childs.btnBack.on(cc.Node.EventType.TOUCH_END, this.onClickBack, this);
        this.childs.btnSetting.on(cc.Node.EventType.TOUCH_END, this.onClickSetting, this);
        UIUtils.setSwallowTouches([ this.childs.btnNumber, this.childs.btnSymbol, this.childs.btnMaterial, this.childs.btnTools ], false);
      },
      onClickNumber: function onClickNumber(event) {
        var self = this;
        cc.resources.load("prefab/NumberPanel", cc.Prefab, function(err, prefab) {
          var uiNode = cc.instantiate(prefab);
          uiNode.parent = self.childs.operateLayer;
          uiNode.getComponent("NumberPanel");
        });
      },
      onClickSymbol: function onClickSymbol(event) {
        var self = this;
        cc.resources.load("prefab/SymbolPanel", cc.Prefab, function(err, prefab) {
          var uiNode = cc.instantiate(prefab);
          uiNode.parent = self.childs.operateLayer;
          uiNode.getComponent("SymbolPanel");
        });
      },
      onClickMaterial: function onClickMaterial(event) {
        var self = this;
        cc.resources.load("prefab/MaterialPanel", cc.Prefab, function(err, prefab) {
          var uiNode = cc.instantiate(prefab);
          uiNode.parent = self.childs.operateLayer;
          uiNode.getComponent("MaterialPanel");
        });
      },
      onClickTools: function onClickTools(event) {
        var self = this;
        cc.resources.load("prefab/ToolsPanel", cc.Prefab, function(err, prefab) {
          var uiNode = cc.instantiate(prefab);
          uiNode.parent = self.childs.operateLayer;
          uiNode.getComponent("NumberPanel");
        });
      },
      onClickBack: function onClickBack(event) {
        CMPlatformClient.callNativeQuit();
      },
      onClickSetting: function onClickSetting(event) {},
      handleBorn: function handleBorn(event) {}
    });
    cc._RF.pop();
  }, {
    "../core/utils/UIUtils": "UIUtils"
  } ],
  MathUtils: [ function(require, module, exports) {
    "use strict";
    cc._RF.push(module, "7013dmYK05GiqC+ekQQhOoR", "MathUtils");
    "use strict";
    window.MathUtils = cc.Class({
      properties: {},
      statics: {
        randomRangeInt: function randomRangeInt(min, max) {
          var rand = Math.random();
          1 === rand && (rand -= Number.EPSILON);
          return min + Math.floor(rand * (max - min));
        },
        randomRangeFLoat: function randomRangeFLoat(min, max) {
          return min + Math.random() * (max - min);
        },
        fmod: function fmod(v1, v2) {
          var temp = Math.floor(v1 / v2);
          return v1 - temp * v2;
        }
      }
    });
    cc._RF.pop();
  }, {} ],
  NumberPanel: [ function(require, module, exports) {
    "use strict";
    cc._RF.push(module, "94839oKTVNMvIB4aA69LvWN", "NumberPanel");
    "use strict";
    var CMBaseUI = require("./CMBaseUI");
    var _require = require("./element/ElementConst"), ElementCategoryEnum = _require.ElementCategoryEnum, NumberEnum = _require.NumberEnum;
    cc.Class({
      extends: CMBaseUI,
      properties: {},
      onLoad: function onLoad() {
        this.allChilds = UIUtils.getAllChildren(this.node);
        for (var i = 1; i <= 9; i++) {
          this.allChilds["num" + i + "Sp"].on(cc.Node.EventType.TOUCH_START, this.onTouchStart, this);
          this.allChilds["num" + i + "Sp"].customTag = i.toString();
        }
      },
      start: function start() {},
      onTouchStart: function onTouchStart(event) {
        console.log(">>>?? onTouchStart");
        this.node.destroy();
        var eleType = event.currentTarget.customTag;
        var eleCategory = ElementCategoryEnum.NumberCategory;
        var pos = event.getLocation();
        CMEventMgr.emit({
          type: "BORN_ELEMENT",
          eleCategory: eleCategory,
          eleType: eleType,
          pos: pos
        });
      }
    });
    cc._RF.pop();
  }, {
    "./CMBaseUI": "CMBaseUI",
    "./element/ElementConst": "ElementConst"
  } ],
  OutOfRangeTouchRemoveSelf: [ function(require, module, exports) {
    "use strict";
    cc._RF.push(module, "ab952+jT3hMZa54uysVnTGT", "OutOfRangeTouchRemoveSelf");
    "use strict";
    var CMConfig = require("../CMConfig");
    cc.Class({
      extends: cc.Component,
      properties: {
        _attachNode: {
          default: null,
          type: cc.Node
        }
      },
      onLoad: function onLoad() {
        var node = new cc.Node();
        node.width = CMConfig.SCREEN_WIDTH;
        node.height = CMConfig.SCREEN_HEIGHT;
        node.parent = cc.Canvas.instance.node;
        node.zIndex = -1;
        node.on(cc.Node.EventType.TOUCH_END, this.onCheckTouchPosition, this);
        this._attachNode = node;
      },
      start: function start() {},
      onDisable: function onDisable() {
        console.log("onDisable");
      },
      onDestroy: function onDestroy() {
        if (this._attachNode) {
          this._attachNode.targetOff();
          this._attachNode.destroy();
        }
      },
      onCheckTouchPosition: function onCheckTouchPosition(event) {
        var pos = this.node.parent.convertToNodeSpaceAR(event.getLocation());
        var rect = this.node.getBoundingBox();
        if (false == rect.contains(pos)) {
          this.node.removeFromParent();
          this.node.destroy();
          this._attachNode.targetOff();
          this._attachNode.destroy();
        }
      }
    });
    cc._RF.pop();
  }, {
    "../CMConfig": "CMConfig"
  } ],
  StringUtils: [ function(require, module, exports) {
    "use strict";
    cc._RF.push(module, "87a97rxNDZCto4SJOX9ej9i", "StringUtils");
    "use strict";
    cc._RF.pop();
  }, {} ],
  SymbolPanel: [ function(require, module, exports) {
    "use strict";
    cc._RF.push(module, "7176crQ2FRO5pH62v6cR4Hw", "SymbolPanel");
    "use strict";
    var CMBaseUI = require("./CMBaseUI");
    var _require = require("./element/ElementConst"), ElementCategoryEnum = _require.ElementCategoryEnum, SymbolEnum = _require.SymbolEnum;
    cc.Class({
      extends: CMBaseUI,
      properties: {},
      onLoad: function onLoad() {
        this.allChilds = UIUtils.getAllChildren(this.node);
        var objs = [ this.allChilds.symbolAdd, this.allChilds.symbolSub, this.allChilds.symbolLeft, this.allChilds.symbolRight, this.allChilds.symbolEqual ];
        for (var i = 0; i < objs.length; i++) {
          objs[i].on(cc.Node.EventType.TOUCH_START, this.onClickStart, this);
          objs[i].customTag = i.toString();
        }
      },
      start: function start() {},
      onClickStart: function onClickStart(event) {
        var glPos = event.getLocation();
        var eleType = event.currentTarget.customTag;
        var eleCategory = ElementCategoryEnum.SymbolCategory;
        CMEventMgr.emit({
          type: "BORN_ELEMENT",
          eleCategory: eleCategory,
          eleType: eleType,
          pos: glPos
        });
        this.node.destroy();
      }
    });
    cc._RF.pop();
  }, {
    "./CMBaseUI": "CMBaseUI",
    "./element/ElementConst": "ElementConst"
  } ],
  ToolsPanel: [ function(require, module, exports) {
    "use strict";
    cc._RF.push(module, "93a49hvhAVOk6nkPukuWukt", "ToolsPanel");
    "use strict";
    var UIUtils = require("../core/utils/UIUtils");
    var CMBaseUI = require("./CMBaseUI");
    var _require = require("./element/ElementConst"), ElementCategoryEnum = _require.ElementCategoryEnum, SymbolEnum = _require.SymbolEnum;
    cc.Class({
      extends: CMBaseUI,
      properties: {},
      onLoad: function onLoad() {
        this.allChilds = UIUtils.getAllChildren(this.node);
        var objs = [ this.allChilds.count, this.allChilds.louds, this.allChilds.categories ];
        for (var i = 0; i < objs.length; i++) {
          objs[i].on(cc.Node.EventType.TOUCH_START, this.onClickStart, this);
          objs[i].customTag = i.toString();
        }
      },
      start: function start() {},
      onClickStart: function onClickStart(event) {
        var glPos = event.getLocation();
        var eleType = event.currentTarget.customTag;
        var eleCategory = ElementCategoryEnum.ToolCategory;
        CMEventMgr.emit({
          type: "BORN_ELEMENT",
          eleCategory: eleCategory,
          eleType: eleType,
          pos: glPos
        });
        this.node.destroy();
      }
    });
    cc._RF.pop();
  }, {
    "../core/utils/UIUtils": "UIUtils",
    "./CMBaseUI": "CMBaseUI",
    "./element/ElementConst": "ElementConst"
  } ],
  UIUtils: [ function(require, module, exports) {
    "use strict";
    cc._RF.push(module, "13cb6a6k3RLl5QlxaKYBtDB", "UIUtils");
    "use strict";
    var UIUtils = {
      getAllChildren: function getAllChildren(rootNode) {
        function getAllChildsByRecursive(parentNode, objListChilds) {
          objListChilds[parentNode.name] = parentNode;
          var listChilds = parentNode.children;
          for (var i = 0; i < listChilds.length; i++) getAllChildsByRecursive(listChilds[i], objListChilds);
        }
        var allChilds = {};
        getAllChildsByRecursive(rootNode, allChilds);
        return allChilds;
      },
      setSwallowTouches: function setSwallowTouches(nodes, isSwallow) {
        nodes && nodes.forEach(function(node) {
          node._touchListener.setSwallowTouches(isSwallow);
        });
      }
    };
    module.exports = UIUtils;
    cc._RF.pop();
  }, {} ]
}, {}, [ "CMConfig", "CMMain", "OutOfRangeTouchRemoveSelf", "BaseUI", "CMEventMgr", "CMLog", "CMAudioManager", "CMTimeManager", "CMUIManager", "CMHttp", "CMMessage", "CMSocket", "CMSocketMgr", "MathUtils", "StringUtils", "UIUtils", "CMDataManager", "MathElementCtr", "MathLand", "CMFormulaForComputing", "CMPlatformClient", "CMBaseUI", "CMUIHelp", "MaterialPanel", "NumberPanel", "SymbolPanel", "ToolsPanel", "ElementConst", "ElementInfo", "ElementMgr", "ElementUI" ]);