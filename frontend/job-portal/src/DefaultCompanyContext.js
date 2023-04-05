import React, { createContext, useState } from 'react';

const DEFAULT_COMPANY_ID = 5

export const DefaultCompanyContext = createContext(DEFAULT_COMPANY_ID);

export const DefaultCompanyProvider = ({ children }) => {
  const [defaultCompanyId, setdefaultCompanyId] = useState(DEFAULT_COMPANY_ID);

  return (
    <DefaultCompanyContext.Provider value={[defaultCompanyId, setdefaultCompanyId ]}>
      {children}
    </DefaultCompanyContext.Provider>
  );
};
