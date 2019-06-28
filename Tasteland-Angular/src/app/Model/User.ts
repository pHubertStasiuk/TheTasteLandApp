export interface User {
  username: string;
  password?: string;
  matchingPassword?: string;
  pictureUrl?: string;
  authorities?: Array<string>;
  firstName?: string;
  lastName?: string;
  dateOfBirth?: Date;
  email?: string;
  gender?: string;
  country?: string;
  }
