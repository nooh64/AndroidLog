# AndroidLog
AndroidLog - Simple Android Logs

##Usage

```java
    ALog.d("Message");
    ALog.d("Message1","Message2","Message3");
    ALog.d(1,2,3,4);
    ALog.setHierarchy(2).setTag("ALOG");
    ALog.e("Message");
    ALog.showHierarchy(false).showHeaderAndFooter(false).d("Hello World");
 ```
 sample  1
 ![sample 1](images/sample1.png)
 
 sample 2
 ![sample 2](images/sample2.png)
## Contributions

Any contributions are welcome! 

## Developed By
* Nooh - <nooh64@gmail.com> 

## License

    Copyright 2014 NOOH KVM

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
