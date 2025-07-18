import {NextFunction, Request, Response} from 'express';
import {body, validationResult} from 'express-validator';

const handleValidationErrors = async (
  req: Request,
  res: Response,
  next: NextFunction,
): Promise<any> => {
  const errors = validationResult(req);

  if (!errors.isEmpty()) {
    return res.status(400).json({errors: errors.array()});
  }
  next();
};

export const validateMyUserRequest = [
  body('name').isString().notEmpty().withMessage('Name is required'),
  body('addressLine1')
    .isString()
    .notEmpty()
    .withMessage('Address Line1 is required'),
  body('city').isString().notEmpty().withMessage('City is required'),
  body('country').isString().notEmpty().withMessage('Country is required'),
  handleValidationErrors,
];
