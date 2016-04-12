# spring-crypto
Spring Crypto 

Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy Files 8
 [Download](http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html)


Verify MD5

    $ find . -name '*.jar' | xargs md5
    MD5 (./local_policy.jar) = dabfcb23d7bf9bf5a201c3f6ea9bfb2c
    MD5 (./US_export_policy.jar) = ef6e8eae7d1876d7f05d765d2c2e0529
    
Replace the original files [MacOS]
    
    sudo cp ~/Downloads/UnlimitedJCEPolicy/local_policy.jar $(find $(/usr/libexec/java_home -v 1.8) -name local_policy.jar)
    sudo cp ~/Downloads/UnlimitedJCEPolicy/US_export_policy.jar $(find $(/usr/libexec/java_home -v 1.8) -name US_export_policy.jar)
